package ru.lephant.learning.spring.SomeFirmWebFlow.thing.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.GroupType;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.ItemType;
import ru.lephant.learning.spring.SomeFirmWebFlow.thing.dao.ThingDAO;

import java.util.List;

@Repository
public class ThingDAOImpl implements ThingDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List listThing() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(Thing.class).list();
        session.close();
        return list;
    }

    @Override
    public List listThing(User user) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Thing.class);
        if (!user.getGroupMember().getGroup().equals(GroupType.BRIGADIER_GROUP.getGroup())) {
            criteria.add(Restrictions.eq("type", ItemType.PRODUCT));
        }
        List list = criteria.list();
        session.close();
        return list;
    }

    @Override
    public Thing getThingByPressmark(long pressmark) {
        Session session = sessionFactory.openSession();
        Thing thing = (Thing) session.createCriteria(Thing.class)
                .add(Restrictions.idEq(pressmark))
                .uniqueResult();
        session.close();
        return thing;
    }

}
