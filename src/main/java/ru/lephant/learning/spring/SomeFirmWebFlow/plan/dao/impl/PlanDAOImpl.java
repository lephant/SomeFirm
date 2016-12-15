package ru.lephant.learning.spring.SomeFirmWebFlow.plan.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Plan;
import ru.lephant.learning.spring.SomeFirmWebFlow.plan.dao.PlanDAO;

import java.io.Serializable;
import java.util.List;

@Repository
public class PlanDAOImpl implements PlanDAO, Serializable {

    @Autowired
    SessionFactory sessionFactory;

    public Plan getPlanById(long id) {
        Session session = sessionFactory.openSession();
        Plan plan = (Plan)session.createCriteria(Plan.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();
        session.close();
        return plan;
    }

    public List listPlan() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(Plan.class).list();
        session.close();
        return list;
    }

    public void deletePlan(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Plan plan = (Plan) session.load(Plan.class, id);
        if (plan != null) session.delete(plan);
        transaction.commit();
        session.close();
    }

    public void savePlan(Plan plan) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(plan);
        transaction.commit();
        session.close();
    }

}
