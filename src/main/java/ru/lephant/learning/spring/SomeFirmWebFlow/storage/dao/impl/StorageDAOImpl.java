package ru.lephant.learning.spring.SomeFirmWebFlow.storage.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageContent;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.dao.StorageDAO;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StorageDAOImpl implements StorageDAO {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public List listStorageContent() {
        Session session = sessionFactory.openSession();
        List list = session
                .createCriteria(StorageContent.class)
                .add(Restrictions.isNull("workshop"))
                .list();
        session.close();
        return list;
    }

    @Override
    public StorageContent getStorageContentByThing(Thing thing) {
        Session session = sessionFactory.openSession();
        StorageContent content = (StorageContent) session
                .createCriteria(StorageContent.class)
                .add(Restrictions
                        .and(
                                Restrictions.isNull("workshop"),
                                Restrictions.eq("thing", thing)
                        )
                )
                .uniqueResult();
        session.close();
        return content;
    }

    @Override
    public void commitStorage(ArrayList<StorageContent> storageContent,
                              ArrayList<StorageJournal> noteList,
                              ArrayList<StorageContent> changedWorkshopContents) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            for (StorageContent content: storageContent) {
                if (content.getCount() == 0) {
                    if (content.getId() == 0) continue;
                    StorageContent dbContent = (StorageContent) session.load(StorageContent.class, content.getId());
                    if (dbContent != null) session.delete(dbContent);
                } else {
                    session.saveOrUpdate(content);
                }
            }

            for (StorageContent content: changedWorkshopContents) {
                session.saveOrUpdate(content);
            }

            for (StorageJournal note: noteList) {
                session.merge(note);
            }

            transaction.commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
