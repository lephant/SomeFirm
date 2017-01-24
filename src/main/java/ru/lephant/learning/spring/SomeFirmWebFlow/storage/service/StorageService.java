package ru.lephant.learning.spring.SomeFirmWebFlow.storage.service;

import org.springframework.binding.message.MessageContext;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageContent;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;

import java.util.ArrayList;
import java.util.List;

public interface StorageService {

    public void writeOffThingFromWorkshop(StorageJournal note,
                                          ArrayList<StorageJournal> noteList,
                                          MessageContext messageContext);

    public void writeOffThingFromStorage(StorageJournal note,
                                         ArrayList<StorageJournal> noteList,
                                         ArrayList<StorageContent> content,
                                         MessageContext messageContext);

    public void sendThingFromWorkshopToStorage(StorageJournal note,
                                               ArrayList<StorageJournal> noteList,
                                               ArrayList<StorageContent> storageContent,
                                               MessageContext messageContext);

    public void sendThingFromStorageToWorkshop(StorageJournal note,
                                               ArrayList<StorageJournal> noteList,
                                               ArrayList<StorageContent> storageContent,
                                               ArrayList<StorageContent> contentsOfWorkshops,
                                               MessageContext messageContext);

    public void importThingToStorage(StorageJournal note,
                                     ArrayList<StorageJournal> noteList,
                                     ArrayList<StorageContent> storageContent,
                                     MessageContext messageContext);

    public List listStorageContent();

    public StorageContent getStorageContentByThing(Thing thing);

    public void commitStorage(ArrayList<StorageContent> storageContent,
                              ArrayList<StorageJournal> noteList,
                              ArrayList<StorageContent> changedWorkshopContents,
                              MessageContext messageContext);

}
