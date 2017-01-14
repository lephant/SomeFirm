package ru.lephant.learning.spring.SomeFirmWebFlow.workshop.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageContent;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;

import java.util.ArrayList;
import java.util.List;

public interface WorkshopDAO {

    public List listWorkshop();

    public List listWorkshopWithAbstractMainStorage();

    public Workshop getWorkshopById(long id);

    public Workshop getLazyWorkshopById(long id);

    public void deleteWorkshop(long id);

    public void saveWorkshop(Workshop workshop, ArrayList<StorageJournal> noteList, ArrayList<StorageContent> storageContent);

}
