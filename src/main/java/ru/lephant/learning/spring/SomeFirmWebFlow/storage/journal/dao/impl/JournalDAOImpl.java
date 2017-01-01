package ru.lephant.learning.spring.SomeFirmWebFlow.storage.journal.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.journal.dao.JournalDAO;

import java.util.List;

@Repository
public class JournalDAOImpl implements JournalDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List listJournal() {
        Session session = sessionFactory.openSession();
        List list = session
                .createCriteria(StorageJournal.class)
                .addOrder(Order.desc("dateAndTime"))
                .list();
        session.close();
        return list;
    }

    public StorageJournal getJournalNoteById(long id) {
        Session session = sessionFactory.openSession();
        StorageJournal note = (StorageJournal) session
                .createCriteria(StorageJournal.class)
                .add(Restrictions.idEq(id))
                .uniqueResult();
        session.close();
        return note;
    }

}
