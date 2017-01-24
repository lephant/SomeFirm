package ru.lephant.learning.spring.SomeFirmWebFlow.storage.journal.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries.StorageJournalSearchCriteria;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.journal.dao.JournalDAO;

import java.util.List;

@Repository
public class JournalDAOImpl implements JournalDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List listJournal(StorageJournalSearchCriteria searchCriteria) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(StorageJournal.class);

        addCriteriaByWorkshop(searchCriteria, criteria);
        addCriteriaByOperationType(searchCriteria, criteria);
        addCriteriaByDate(searchCriteria, criteria);

        criteria.addOrder(Order.desc("dateAndTime"));
        List list = criteria.list();

        session.close();
        return list;
    }

    private void addCriteriaByDate(StorageJournalSearchCriteria searchCriteria, Criteria criteria) {
        if (searchCriteria.isSearchByDate()) {
            criteria.add(Restrictions.gt("dateAndTime", searchCriteria.getMinTime()));
            criteria.add(Restrictions.lt("dateAndTime", searchCriteria.getMaxTime()));
        }
    }

    private void addCriteriaByOperationType(StorageJournalSearchCriteria searchCriteria, Criteria criteria) {
        if (searchCriteria.getOperationType() != null) {
            criteria.add(Restrictions.eq("journalOperationType",searchCriteria.getOperationType()));
        }
    }

    private void addCriteriaByWorkshop(StorageJournalSearchCriteria searchCriteria, Criteria criteria) {
        if (searchCriteria.getWorkshop() != null) {
            if (searchCriteria.getWorkshop().getId() == 0) {
                criteria.add(Restrictions.isNull("workshop"));
            } else {
                criteria.add(Restrictions.eq("workshop", searchCriteria.getWorkshop()));
            }
        }
    }

    @Override
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