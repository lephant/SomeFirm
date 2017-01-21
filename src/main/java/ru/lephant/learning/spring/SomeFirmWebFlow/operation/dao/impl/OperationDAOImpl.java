package ru.lephant.learning.spring.SomeFirmWebFlow.operation.dao.impl;

import org.hibernate.*;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Operation;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Plan;
import ru.lephant.learning.spring.SomeFirmWebFlow.operation.FileUploadBean;
import ru.lephant.learning.spring.SomeFirmWebFlow.operation.dao.OperationDAO;

import java.io.Serializable;
import java.util.List;

@Repository
public class OperationDAOImpl implements OperationDAO, Serializable {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public Operation getOperationById(long id) {
        Session session = sessionFactory.openSession();
        Operation operation = (Operation) session
                .createCriteria(Operation.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();

        Hibernate.initialize(operation.getPlan());
        Hibernate.initialize(operation.getSacrificialMaterials());
        Hibernate.initialize(operation.getTools());

        session.close();
        return operation;
    }

    @Override
    public Operation getLazyOperationById(long id) {
        Session session = sessionFactory.openSession();
        Operation operation = (Operation) session
                .createCriteria(Operation.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();
        session.close();
        return operation;
    }

    @Override
    public byte[] getPlanOfOperationById(long id) {
        Session session = sessionFactory.openSession();
        byte[] image = (byte[]) session
                .createCriteria(Operation.class)
                .add(Restrictions.idEq(id))
                .createAlias("plan", "plan")
                .setProjection(
                        Projections.property("plan.content")
                )
                .uniqueResult();
        session.close();
        return image;
    }

    @Override
    public List listOperation() {
        Session session = sessionFactory.openSession();
        List list = session
                .createCriteria(Operation.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        session.close();
        return list;
    }

    @Override
    public void deleteOperation(long id)
            throws ConstraintViolationException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Operation operation = (Operation) session.load(Operation.class, id);
            if (operation != null) session.delete(operation);
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveOperation(Operation operation, FileUploadBean fileUploadBean)
            throws ConstraintViolationException {
        fillPlanOfNecessity(operation, fileUploadBean);
        Session session = null;
        try {
            session= sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(operation);
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private void fillPlanOfNecessity(Operation operation, FileUploadBean fileUploadBean) {
        if (fileUploadBean.getData() != null) {
            if (operation.getPlan() == null) {
                operation.setPlan(new Plan());
            }
            operation.getPlan().setContent(fileUploadBean.getData());
        }
    }
}
