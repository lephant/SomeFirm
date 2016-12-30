package ru.lephant.learning.spring.SomeFirmWebFlow.storage.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageContent;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;

import java.util.ArrayList;
import java.util.List;

public interface StorageDAO {

    public List listStorageContent();

    public StorageContent getStorageContentByThing(Thing thing);

    public void commitStorage(ArrayList<StorageContent> storageContent, ArrayList<StorageJournal> noteList);

}
