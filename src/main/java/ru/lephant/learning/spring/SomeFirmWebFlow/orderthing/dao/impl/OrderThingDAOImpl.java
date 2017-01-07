package ru.lephant.learning.spring.SomeFirmWebFlow.orderthing.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.OrderThing;
import ru.lephant.learning.spring.SomeFirmWebFlow.orderthing.dao.OrderThingDAO;

import java.util.List;

@Repository
public class OrderThingDAOImpl implements OrderThingDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List listOrders() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(OrderThing.class).list();
        session.close();
        return list;
    }

    @Override
    public OrderThing getOrderById(long id) {
        Session session = sessionFactory.openSession();
        OrderThing orderThing = (OrderThing) session
                .createCriteria(OrderThing.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();
        session.close();
        return orderThing;
    }

    @Override
    public void changeOrderState(OrderThing orderThing) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(orderThing);
        transaction.commit();
        session.close();
    }

    @Override
    public void createOrder(OrderThing orderThing) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(orderThing);
        transaction.commit();
        session.close();
    }
}
