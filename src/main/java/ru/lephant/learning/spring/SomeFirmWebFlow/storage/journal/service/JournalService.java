package ru.lephant.learning.spring.SomeFirmWebFlow.storage.journal.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;

import java.util.List;

public interface JournalService {

    public List listJournal();

    public StorageJournal getJournalNoteById(long id);

}
