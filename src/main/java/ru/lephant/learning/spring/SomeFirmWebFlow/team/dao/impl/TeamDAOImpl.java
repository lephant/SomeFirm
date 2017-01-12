package ru.lephant.learning.spring.SomeFirmWebFlow.team.dao.impl;

import org.hibernate.*;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Team;
import ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries.TeamSearchCriteria;
import ru.lephant.learning.spring.SomeFirmWebFlow.team.dao.TeamDAO;

import java.util.List;

@Repository
public class TeamDAOImpl implements TeamDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List listTeam(TeamSearchCriteria searchCriteria) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Team.class);

        if (searchCriteria.getThingName() != null && !searchCriteria.getThingName().isEmpty()) {
            criteria.createAlias("order.thing", "thing");
            criteria.add(Restrictions.ilike("thing.name", searchCriteria.getThingName(), MatchMode.ANYWHERE));
        }

        if (searchCriteria.getSearchType() != null) {
            long currentDate = System.currentTimeMillis();
            switch (searchCriteria.getSearchType()) {
                case FINISHED:
                    criteria.add(Restrictions.lt("dateOfDeadline", currentDate));
                    break;
                case ACTUAL:
                    criteria.add(Restrictions.and(
                            Restrictions.le("dateOfCreate", currentDate),
                            Restrictions.ge("dateOfDeadline", currentDate)
                    ));
                    break;
                case UPCOMING:
                    criteria.add(Restrictions.gt("dateOfCreate", currentDate));
                    break;
            }
        }

        List list = criteria.list();

        session.close();
        return list;
    }

    @Override
    public Team getTeamById(long id) {
        Session session = sessionFactory.openSession();
        Team team = (Team) session
                .createCriteria(Team.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();
        Hibernate.initialize(team.getWorkers());
        session.close();
        return team;
    }

    @Override
    public void saveTeam(Team team) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(team);
        transaction.commit();
        session.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Team> getActualTeamsWithWorkersByTime(long from, long to) {
        Session session = sessionFactory.openSession();
        List<Team> list = session
                .createCriteria(Team.class)
                .add(
                        Restrictions.and(
                                Restrictions.not(
                                        Restrictions.and(
                                                Restrictions.gt("dateOfCreate", from),
                                                Restrictions.gt("dateOfDeadline", from),
                                                Restrictions.gt("dateOfCreate", to),
                                                Restrictions.gt("dateOfDeadline", to)
                                        )
                                ),
                                Restrictions.not(
                                        Restrictions.and(
                                                Restrictions.lt("dateOfCreate", from),
                                                Restrictions.lt("dateOfDeadline", from),
                                                Restrictions.lt("dateOfCreate", to),
                                                Restrictions.lt("dateOfDeadline", to)
                                        )
                                )
                        )
                )
                .list();
        for (Team team : list) {
            Hibernate.initialize(team.getWorkers());
        }
        session.close();
        return list;
    }
}
