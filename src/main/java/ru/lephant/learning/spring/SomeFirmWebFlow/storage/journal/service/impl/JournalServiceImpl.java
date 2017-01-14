package ru.lephant.learning.spring.SomeFirmWebFlow.storage.journal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries.StorageJournalSearchCriteria;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.journal.dao.JournalDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.journal.service.JournalService;

import java.util.List;

@Service("journalService")
public class JournalServiceImpl implements JournalService {

    @Autowired
    JournalDAO journalDAO;

    @Transactional(readOnly = true)
    public List listJournal(StorageJournalSearchCriteria searchCriteria) {
        return journalDAO.listJournal(searchCriteria);
    }

    @Transactional(readOnly = true)
    public StorageJournal getJournalNoteById(long id) {
        return journalDAO.getJournalNoteById(id);
    }
}
