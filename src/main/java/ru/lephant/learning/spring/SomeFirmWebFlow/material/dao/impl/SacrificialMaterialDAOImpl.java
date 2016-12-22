package ru.lephant.learning.spring.SomeFirmWebFlow.material.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Pressmarks;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.SacrificialMaterialType;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.ItemType;
import ru.lephant.learning.spring.SomeFirmWebFlow.material.dao.SacrificialMaterialDAO;

import java.util.List;

@Repository
public class SacrificialMaterialDAOImpl implements SacrificialMaterialDAO {

    @Autowired
    SessionFactory sessionFactory;

    public SacrificialMaterialType getSacrificialMaterialByPressmark(long pressmark) {
        Session session = sessionFactory.openSession();
        SacrificialMaterialType sacrificialMaterial = (SacrificialMaterialType) session
                .createCriteria(SacrificialMaterialType.class)
                .add(Restrictions.idEq(pressmark))
                .uniqueResult();
        session.close();
        return sacrificialMaterial;
    }

    public List listSacrificialMaterial() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(SacrificialMaterialType.class).list();
        session.close();
        return list;
    }

    public void saveSacrificialMaterial(SacrificialMaterialType sacrificialMaterial) {
        Session session = sessionFactory.openSession();
        if (sacrificialMaterial.getPressmarks() == null) {
            Pressmarks pressmarks = new Pressmarks(sacrificialMaterial.getPressmark(), ItemType.SACRIFICIAL_MATERIAL);
            sacrificialMaterial.setPressmarks(pressmarks);
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(pressmarks);
            transaction.commit();
        }
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(sacrificialMaterial);
        transaction.commit();
        session.close();
    }

    public void deleteSacrificialMaterial(long pressmark) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        SacrificialMaterialType sacrificialMaterial = (SacrificialMaterialType) session
                .load(SacrificialMaterialType.class, pressmark);
        if (sacrificialMaterial != null) {
            session.delete(sacrificialMaterial);
            session.delete(sacrificialMaterial.getPressmarks());
        }
        transaction.commit();
        session.close();
    }

}