package ru.lephant.learning.spring.SomeFirmWebFlow.orderthing.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.OrderThing;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.User;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.GroupType;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.ItemType;
import ru.lephant.learning.spring.SomeFirmWebFlow.orderthing.dao.OrderThingDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries.OrderThingSearchCriteria;

import java.util.List;

@Repository
public class OrderThingDAOImpl implements OrderThingDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List listOrders(OrderThingSearchCriteria searchCriteria) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(OrderThing.class);
        if (searchCriteria.getOrderState() != null) {
            criteria.add(Restrictions.eq("state", searchCriteria.getOrderState()));
        }
        List list = criteria.list();
        session.close();
        return list;
    }

    @Override
    public List listOrders(OrderThingSearchCriteria searchCriteria, User user) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(OrderThing.class);
        if (searchCriteria.getOrderState() != null) {
            criteria.add(Restrictions.eq("state", searchCriteria.getOrderState()));
        }
        addCriteriaByUser(user, criteria);
        List list = criteria.list();
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

    private void addCriteriaByUser(User user, Criteria criteria) {
        criteria.createAlias("thing", "thing");
        if (user.getGroupMember().getGroup().equals(GroupType.MANAGER_GROUP.getGroup())) {
            criteria.add(Restrictions.eq("thing.type", ItemType.PRODUCT));
        }
        if (user.getGroupMember().getGroup().equals(GroupType.STOREKEEPER_GROUP.getGroup())) {
            criteria.add(
                    Restrictions.or(
                            Restrictions.eq("thing.type", ItemType.TOOL),
                            Restrictions.eq("thing.type", ItemType.SACRIFICIAL_MATERIAL)
                    )
            );
        }
    }
}
