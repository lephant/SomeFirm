package ru.lephant.learning.spring.SomeFirmWebFlow.operation.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Operation;
import ru.lephant.learning.spring.SomeFirmWebFlow.operation.dao.OperationDAO;

import java.io.Serializable;
import java.util.List;

@Repository
public class OperationDAOImpl implements OperationDAO, Serializable {

    @Autowired
    private SessionFactory sessionFactory;


    public Operation getOperationById(long id) {
        Session session = sessionFactory.openSession();
        Operation operation = (Operation) session
                .createCriteria(Operation.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();

        Hibernate.initialize(operation.getSacrificialMaterials());
        Hibernate.initialize(operation.getTools());

        session.close();
        return operation;
    }

    public List listOperation() {
        Session session = sessionFactory.openSession();
        List list = session
                .createCriteria(Operation.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        session.close();
        return list;
    }

    public void deleteOperation(long id) {
        Session session = sessionFactory.openSession();
        Operation operation = (Operation) session.load(Operation.class, id);
        if (operation != null) session.delete(operation);
        session.close();
    }

    public void saveOperation(Operation operation) {
        Session session = sessionFactory.openSession();
        session.saveOrUpdate(operation);
        session.close();
    }
}
