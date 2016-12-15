package ru.lephant.learning.spring.SomeFirmWebFlow.workshop.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;
import ru.lephant.learning.spring.SomeFirmWebFlow.workshop.dao.WorkshopDAO;

import java.util.List;

@Repository
public class WorkshopDAOImpl implements WorkshopDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List listWorkshop() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(Workshop.class).list();
        session.close();
        return list;
    }

    public Workshop getWorkshopById(long id) {
        Session session = sessionFactory.openSession();
        Workshop workshop = (Workshop)session.createCriteria(Workshop.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();
        Hibernate.initialize(workshop.getContents());
        session.close();
        return null;
    }

    public void deleteWorkshop(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Workshop workshop = (Workshop) session.load(Workshop.class, id);
        if (workshop != null) session.delete(workshop);
        transaction.commit();
        session.close();
    }

    public void saveWorkshop(Workshop workshop) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(workshop);
        transaction.commit();
        session.close();
    }

}
