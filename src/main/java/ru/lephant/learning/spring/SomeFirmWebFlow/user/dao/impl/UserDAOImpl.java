package ru.lephant.learning.spring.SomeFirmWebFlow.user.dao.impl;

import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;
import ru.lephant.learning.spring.SomeFirmWebFlow.user.dao.UserDAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO {

    public static final String WORKER_GROUP_NAME = "workers";


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
    public User getLazyUserByUsername(String username) {
        Session session = sessionFactory.openSession();
        User user = (User) session
                .createCriteria(User.class)
                .add(Restrictions.idEq(username))
                .uniqueResult();
        session.close();
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = sessionFactory.openSession();
        User user = (User) session
                .createCriteria(User.class)
                .add(Restrictions.idEq(username))
                .uniqueResult();
        Hibernate.initialize(user.getOrders());
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
                .createAlias("groupMember", "groupMember")
                .createAlias("groupMember.group", "group")
                .add(Restrictions.ilike("group.groupName", WORKER_GROUP_NAME));

        if (!usernames.isEmpty()) {
            criteria.add(Restrictions.not(
                    Restrictions.in("username", usernames)
            ));
        }

        List list = criteria.list();

        session.close();
        return list;
    }

    @Override
    public void registerUser(User user) throws ConstraintViolationException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
