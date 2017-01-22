package ru.lephant.learning.spring.SomeFirmWebFlow.storage.employe.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageEmploye;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.employe.dao.StorageEmployeDAO;
import java.util.List;

@Repository
public class StorageEmployeDAOImpl implements StorageEmployeDAO  {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List listStorageEmploye() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(StorageEmploye.class).list();
        session.close();
        return list;
    }

    @Override
    public StorageEmploye getStorageEmployeById(long id) {
        Session session = sessionFactory.openSession();
        StorageEmploye storageEmploye = (StorageEmploye)session
                .createCriteria(StorageEmploye.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();
        session.close();
        return storageEmploye;
    }

    @Override
    public void saveStorageEmploye(StorageEmploye storageEmploye) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(storageEmploye);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteStorageEmploye(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        StorageEmploye storageEmploye = (StorageEmploye) session.load(StorageEmploye.class, id);
        if(storageEmploye != null) session.delete(storageEmploye);
        transaction.commit();
        session.close();
    }

}
