package ru.lephant.learning.spring.SomeFirmWebFlow.storage.journal.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries.StorageJournalSearchCriteria;

import java.util.List;

public interface JournalDAO {

    public List listJournal(StorageJournalSearchCriteria searchCriteria);

    public StorageJournal getJournalNoteById(long id);

}
