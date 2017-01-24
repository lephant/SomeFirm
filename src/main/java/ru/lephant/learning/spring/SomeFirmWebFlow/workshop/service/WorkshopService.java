package ru.lephant.learning.spring.SomeFirmWebFlow.workshop.service;

import org.springframework.binding.message.MessageContext;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageContent;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;

import java.util.ArrayList;
import java.util.List;

public interface WorkshopService {

    public List listWorkshop();

    public List listWorkshopWithAbstractMainStorage();

    public Workshop getWorkshopById(long id);

    public Workshop getLazyWorkshopById(long id);

    public boolean deleteWorkshop(long id, MessageContext messageContext);

    public void saveWorkshop(Workshop workshop,
                             ArrayList<StorageJournal> noteList,
                             ArrayList<StorageContent> storageContent,
                             MessageContext messageContext);

}
