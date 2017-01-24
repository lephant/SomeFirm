package ru.lephant.learning.spring.SomeFirmWebFlow.material.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.SacrificialMaterialType;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.ItemType;
import ru.lephant.learning.spring.SomeFirmWebFlow.material.dao.SacrificialMaterialDAO;

import java.util.List;

@Repository
public class SacrificialMaterialDAOImpl implements SacrificialMaterialDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public SacrificialMaterialType getSacrificialMaterialByPressmark(long pressmark) {
        Session session = sessionFactory.openSession();
        SacrificialMaterialType sacrificialMaterial = (SacrificialMaterialType) session
                .createCriteria(SacrificialMaterialType.class)
                .add(Restrictions.idEq(pressmark))
                .uniqueResult();
        session.close();
        return sacrificialMaterial;
    }

    @Override
    public List listSacrificialMaterial() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(SacrificialMaterialType.class).list();
        session.close();
        return list;
    }

    @Override
    public void createSacrificialMaterial(SacrificialMaterialType sacrificialMaterial)
            throws ConstraintViolationException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            sacrificialMaterial.setType(ItemType.SACRIFICIAL_MATERIAL);
            session.save(sacrificialMaterial);
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void updateSacrificialMaterial(SacrificialMaterialType sacrificialMaterial)
            throws ConstraintViolationException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(sacrificialMaterial);
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void deleteSacrificialMaterial(long pressmark)
            throws ConstraintViolationException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            SacrificialMaterialType sacrificialMaterial = (SacrificialMaterialType) session
                    .load(SacrificialMaterialType.class, pressmark);
            if (sacrificialMaterial != null) session.delete(sacrificialMaterial);
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
