package ru.lephant.learning.spring.SomeFirmWebFlow.storage.journal.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;

import java.util.List;

public interface JournalDAO {

    public List listJournal();

    public StorageJournal getJournalNoteById(long id);

}
