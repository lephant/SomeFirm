package ru.lephant.learning.spring.SomeFirmWebFlow.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageContent;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;
import ru.lephant.learning.spring.SomeFirmWebFlow.reference.ReferenceData;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.dao.StorageDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.service.StorageService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("storageService")
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageDAO storageDAO;


    public void writeOffThingFromWorkshop(StorageJournal note, ArrayList<StorageJournal> noteList) {
        StorageContent currentContentInWorkshop = note.getWorkshop().getContentByThing(note.getThing());

        if (note.getCount() <= 0) return; // TODO: сделать валидаторы

        if (currentContentInWorkshop == null || currentContentInWorkshop.getCount() < note.getCount()) {
            return; // TODO: сделать обработку ошибок
        }

        currentContentInWorkshop.setCount(currentContentInWorkshop.getCount() - note.getCount());

        note.setDateAndTime(new Date().getTime());
        note.setJournalOperationType(ReferenceData.JournalOperationTypes.writeOffFromWorkshopOperation);

        noteList.add(note);
    }


    public void writeOffThingFromStorage(StorageJournal note, ArrayList<StorageJournal> noteList,
                                         ArrayList<StorageContent> storageContent) {
        StorageContent currentContentInStorage = getContentOfStorageByThing(storageContent, note.getThing());

        if (note.getCount() <= 0) return; // TODO: сделать валидаторы

        if (currentContentInStorage == null || currentContentInStorage.getCount() < note.getCount()) {
            return; // TODO: сделать обработку ошибок
        }

        currentContentInStorage.setCount(currentContentInStorage.getCount() - note.getCount());

        note.setDateAndTime(new Date().getTime());
        note.setJournalOperationType(ReferenceData.JournalOperationTypes.writeOffFromStorageOperation);

        noteList.add(note);
    }


    public void sendThingFromWorkshopToStorage(StorageJournal note, ArrayList<StorageJournal> noteList,
                                               ArrayList<StorageContent> storageContent) {

        StorageContent currentContentInWorkshop = note.getWorkshop().getContentByThing(note.getThing());

        if (note.getCount() <= 0) return; // TODO: сделать валидаторы

        if (currentContentInWorkshop == null || currentContentInWorkshop.getCount() < note.getCount()) {
            return; // TODO: сделать обработку ошибок
        }

        StorageContent currentContentInStorage = getContentOfStorageByThing(storageContent, note.getThing());

        if (currentContentInStorage == null) {
            currentContentInStorage = createContentByNote(note);
            storageContent.add(currentContentInStorage);
        }

        currentContentInStorage.setCount(currentContentInStorage.getCount() + note.getCount());
        currentContentInWorkshop.setCount(currentContentInWorkshop.getCount() - note.getCount());

        note.setDateAndTime(new Date().getTime());
        note.setJournalOperationType(ReferenceData.JournalOperationTypes.sendFromWorkshopToStorageOperation);

        noteList.add(note);
    }



    public void sendThingFromStorageToWorkshop(Thing thing, Workshop workshop, int count) {

    }

    public void importThingToStorage(StorageJournal note, ArrayList<StorageJournal> noteList,
                                     ArrayList<StorageContent> storageContent) {
        StorageContent currentContentInStorage = getContentOfStorageByThing(storageContent, note.getThing());

        if (note.getCount() <= 0) return; // TODO: сделать валидаторы

        if (currentContentInStorage == null) {
            currentContentInStorage = createContentByNote(note);
            storageContent.add(currentContentInStorage);
        }

        currentContentInStorage.setCount(currentContentInStorage.getCount() + note.getCount());

        note.setDateAndTime(new Date().getTime());
        note.setJournalOperationType(ReferenceData.JournalOperationTypes.importOperation);

        noteList.add(note);
    }


    @Transactional(readOnly = true)
    public List listStorageContent() {
        return storageDAO.listStorageContent();
    }

    @Transactional(readOnly = true)
    public StorageContent getStorageContentByThing(Thing thing) {
        return storageDAO.getStorageContentByThing(thing);
    }

    @Transactional
    public void commitStorage(ArrayList<StorageContent> storageContent, ArrayList<StorageJournal> noteList) {
        storageDAO.commitStorage(storageContent, noteList);
    }


    private StorageContent getContentOfStorageByThing(ArrayList<StorageContent> storageContent, Thing thing) {
        StorageContent contentInStorage = null;
        for (StorageContent content : storageContent) {
            if (content.getThing().equals(thing)) {
                contentInStorage = content;
                break;
            }
        }
        return contentInStorage;
    }

    private StorageContent createContentByNote(StorageJournal note) {
        StorageContent contentInStorage;
        contentInStorage = new StorageContent();
        contentInStorage.setWorkshop(null);
        contentInStorage.setThing(note.getThing());
        contentInStorage.setCount(0);
        return contentInStorage;
    }

}
