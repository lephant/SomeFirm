package ru.lephant.learning.spring.SomeFirmWebFlow.storage.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageContent;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;

import java.util.ArrayList;
import java.util.List;

public interface StorageService {

    public void writeOffThingFromWorkshop(StorageJournal note, ArrayList<StorageJournal> noteList);

    public void writeOffThingFromStorage(Thing thing, int count);

    public void sendThingFromWorkshopToStorage(StorageJournal note, ArrayList<StorageJournal> noteList,
                                               ArrayList<StorageContent> storageContent);

    public void sendThingFromStorageToWorkshop(Thing thing, Workshop workshop, int count);

    public void importThingToStorage(Thing thing, int count);

    public List listStorageContent();

    public StorageContent getStorageContentByThing(Thing thing);

}
