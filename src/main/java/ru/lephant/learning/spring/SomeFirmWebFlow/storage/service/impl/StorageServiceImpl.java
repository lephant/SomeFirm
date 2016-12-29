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
        StorageContent contentInWorkshop = note.getWorkshop().getContentByThing(note.getThing());

        if (note.getCount() <= 0) return; // TODO: сделать валидаторы

        if (contentInWorkshop == null || contentInWorkshop.getCount() < note.getCount()) {
            return; // TODO: сделать обработку ошибок
        }

        contentInWorkshop.setCount(contentInWorkshop.getCount() - note.getCount());

        note.setDateAndTime(new Date().getTime());
        note.setJournalOperationType(ReferenceData.JournalOperationTypes.writeOffFromWorkshopOperation);

        noteList.add(note);
    }

    public void writeOffThingFromStorage(Thing thing, int count) {

    }

    public void sendThingFromWorkshopToStorage(StorageJournal note, ArrayList<StorageJournal> noteList,
                                               ArrayList<StorageContent> storageContent) {
        StorageContent contentInWorkshop = note.getWorkshop().getContentByThing(note.getThing());

        if (note.getCount() <= 0) return; // TODO: сделать валидаторы

        if (contentInWorkshop == null || contentInWorkshop.getCount() < note.getCount()) {
            return; // TODO: сделать обработку ошибок
        }

        StorageContent contentInStorage = getContentOfStorageByThing(storageContent, note.getThing());

        if (contentInStorage == null) {
            contentInStorage = createContentByNote(note);
            storageContent.add(contentInStorage);
        }

        contentInStorage.setCount(contentInStorage.getCount() + note.getCount());
        contentInWorkshop.setCount(contentInWorkshop.getCount() - note.getCount());

        note.setDateAndTime(new Date().getTime());
        note.setJournalOperationType(ReferenceData.JournalOperationTypes.sendFromWorkshopToStorageOperation);

        noteList.add(note);
    }



    public void sendThingFromStorageToWorkshop(Thing thing, Workshop workshop, int count) {

    }

    public void importThingToStorage(Thing thing, int count) {

    }


    @Transactional(readOnly = true)
    public List listStorageContent() {
        return storageDAO.listStorageContent();
    }

    @Transactional(readOnly = true)
    public StorageContent getStorageContentByThing(Thing thing) {
        return storageDAO.getStorageContentByThing(thing);
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
