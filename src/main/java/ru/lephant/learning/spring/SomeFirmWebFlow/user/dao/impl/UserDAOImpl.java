package ru.lephant.learning.spring.SomeFirmWebFlow.user.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;
import ru.lephant.learning.spring.SomeFirmWebFlow.user.dao.UserDAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO {

    public static final long WORKER_GROUP_ID = 2L;


    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List listUser() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(User.class).list();
        session.close();
        return list;
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = sessionFactory.openSession();
        User user = (User) session
                .createCriteria(User.class)
                .add(Restrictions.idEq(username))
                .uniqueResult();
        session.close();
        return user;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAnotherWorkers(Set<User> engagedWorkers) {
        Session session = sessionFactory.openSession();

        Set<String> usernames = new HashSet<>();
        for (User user : engagedWorkers) {
            usernames.add(user.getUsername());
        }

        Criteria criteria = session
                .createCriteria(User.class)
                .createAlias("groupMember", "group")
                .add(Restrictions.eq("group.groupId", WORKER_GROUP_ID));

        if (!usernames.isEmpty()) {
            criteria.add(Restrictions.not(
                    Restrictions.in("username", usernames)
            ));
        }

        List list = criteria.list();

        session.close();
        return list;
    }
}
