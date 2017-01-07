package ru.lephant.learning.spring.SomeFirmWebFlow.user.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;
import ru.lephant.learning.spring.SomeFirmWebFlow.user.dao.UserDAO;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

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
}
