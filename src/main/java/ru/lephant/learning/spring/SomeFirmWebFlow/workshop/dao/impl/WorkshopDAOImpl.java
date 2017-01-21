package ru.lephant.learning.spring.SomeFirmWebFlow.workshop.dao.impl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageContent;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;
import ru.lephant.learning.spring.SomeFirmWebFlow.workshop.dao.WorkshopDAO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WorkshopDAOImpl implements WorkshopDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List listWorkshop() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(Workshop.class).list();
        session.close();
        return list;
    }

    @Override
    public List listWorkshopWithAbstractMainStorage() {
        Session session = sessionFactory.openSession();
        List list = session.createCriteria(Workshop.class).list();
        addAbstractMainStorageToList(list);
        session.close();
        return list;
    }

    private void addAbstractMainStorageToList(List list) {
        Workshop storage = new Workshop();
        storage.setName("Главный склад");
        list.add(0, storage);
    }

    @Override
    public Workshop getWorkshopById(long id) {
        Session session = sessionFactory.openSession();
        Workshop workshop = (Workshop) session.createCriteria(Workshop.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();
        Hibernate.initialize(workshop.getContents());
        session.close();
        return workshop;
    }

    @Override
    public Workshop getLazyWorkshopById(long id) {
        Session session = sessionFactory.openSession();
        Workshop workshop = (Workshop) session.createCriteria(Workshop.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();
        session.close();
        return workshop;
    }

    @Override
    public void deleteWorkshop(long id)
            throws ConstraintViolationException {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Workshop workshop = (Workshop) session.load(Workshop.class, id);
            if (workshop != null) session.delete(workshop);
            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void saveWorkshop(Workshop workshop, ArrayList<StorageJournal> noteList, ArrayList<StorageContent> storageContent) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            removeEmptyContents(workshop);

            session.saveOrUpdate(workshop);

            if (storageContent != null) {
                for (StorageContent content : storageContent) {
                    session.saveOrUpdate(content);
                }
            }

            if (noteList != null) {
                for (StorageJournal storageJournal : noteList) {
                    session.save(storageJournal);
                }
            }

            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private void removeEmptyContents(Workshop workshop) {
        boolean check = false;
        while (!check) {
            check = true;
            for (StorageContent content : workshop.getContents()) {
                if (content.getCount() == 0) {
                    workshop.getContents().remove(content);
                    check = false;
                    break;
                }
            }
        }
    }

}
