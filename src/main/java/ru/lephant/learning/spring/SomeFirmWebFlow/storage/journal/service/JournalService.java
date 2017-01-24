package ru.lephant.learning.spring.SomeFirmWebFlow.storage.journal.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries.StorageJournalSearchCriteria;

import java.util.List;

public interface JournalService {

    public List listJournal(StorageJournalSearchCriteria searchCriteria);

    public StorageJournal getJournalNoteById(long id);

}
