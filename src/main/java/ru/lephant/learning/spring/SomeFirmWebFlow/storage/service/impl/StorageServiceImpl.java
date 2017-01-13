package ru.lephant.learning.spring.SomeFirmWebFlow.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageContent;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.JournalOperationType;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.dao.StorageDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.storage.service.StorageService;
import ru.lephant.learning.spring.SomeFirmWebFlow.workshop.service.WorkshopService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("storageService")
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageDAO storageDAO;

    @Autowired
    WorkshopService workshopService;


    public void writeOffThingFromWorkshop(StorageJournal note, ArrayList<StorageJournal> noteList) {
        StorageContent currentContentInWorkshop = note.getWorkshop().getContentByThing(note.getThing());

        if (note.getCount() <= 0) return; // TODO: сделать валидаторы

        if (currentContentInWorkshop == null || currentContentInWorkshop.getCount() < note.getCount()) {
            return; // TODO: сделать обработку ошибок
        }

        currentContentInWorkshop.setCount(currentContentInWorkshop.getCount() - note.getCount());

        note.setDateAndTime(new Date().getTime());
        note.setJournalOperationType(JournalOperationType.WRITE_OFF_FROM_WORKSHOP_OPERATION);

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
        note.setJournalOperationType(JournalOperationType.WRITE_OFF_FROM_STORAGE_OPERATION);

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
        note.setJournalOperationType(JournalOperationType.SEND_FROM_WORKSHOP_TO_STORAGE_OPERATION);

        noteList.add(note);
    }


    public void sendThingFromStorageToWorkshop(StorageJournal note, ArrayList<StorageJournal> noteList,
                                               ArrayList<StorageContent> storageContent,
                                               ArrayList<StorageContent> contentsOfWorkshops) {
        StorageContent currentContentInStorage = getContentOfStorageByThing(storageContent, note.getThing());

        if (note.getCount() <= 0) return; // TODO: сделать валидаторы

        if (currentContentInStorage == null || currentContentInStorage.getCount() < note.getCount()) {
            return; // TODO: сделать обработку ошибок
        }

        StorageContent currentContentInWorkshop = getContentOfStorageByThing(contentsOfWorkshops, note.getThing());
        if (currentContentInWorkshop == null) {
            currentContentInWorkshop = workshopService
                    .getWorkshopById(note.getWorkshop().getId())
                    .getContentByThing(note.getThing());

            if (currentContentInWorkshop == null)
                currentContentInWorkshop = createContentByNote(note);

            contentsOfWorkshops.add(currentContentInWorkshop);
        }

        currentContentInStorage.setCount(currentContentInStorage.getCount() - note.getCount());
        currentContentInWorkshop.setCount(currentContentInWorkshop.getCount() + note.getCount());

        note.setDateAndTime(new Date().getTime());
        note.setJournalOperationType(JournalOperationType.SEND_FROM_STORAGE_TO_WORKSHOP_OPERATION);

        noteList.add(note);
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
        note.setJournalOperationType(JournalOperationType.IMPORT_OPERATION);

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
    public void commitStorage(ArrayList<StorageContent> storageContent, ArrayList<StorageJournal> noteList,
                              ArrayList<StorageContent> changedWorkshopContents) {
        storageDAO.commitStorage(storageContent, noteList, changedWorkshopContents);
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
        contentInStorage.setWorkshop(note.getWorkshop());
        contentInStorage.setThing(note.getThing());
        contentInStorage.setCount(0);
        return contentInStorage;
    }

}
