package ru.lephant.learning.spring.SomeFirmWebFlow.workshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageContent;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;
import ru.lephant.learning.spring.SomeFirmWebFlow.workshop.dao.WorkshopDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.workshop.service.WorkshopService;

import java.util.ArrayList;
import java.util.List;

@Service("workshopService")
public class WorkshopServiceImpl implements WorkshopService {

    @Autowired
    WorkshopDAO workshopDAO;

    @Transactional(readOnly = true)
    public List listWorkshop() {
        return workshopDAO.listWorkshop();
    }

    @Transactional(readOnly = true)
    public Workshop getWorkshopById(long id) {
        return workshopDAO.getWorkshopById(id);
    }

    @Transactional(readOnly = true)
    public Workshop getLazyWorkshopById(long id) {
        return workshopDAO.getLazyWorkshopById(id);
    }

    @Transactional
    public void deleteWorkshop(long id) {
        workshopDAO.deleteWorkshop(id);
    }

    @Transactional
    public void saveWorkshop(Workshop workshop, ArrayList<StorageJournal> noteList, ArrayList<StorageContent> storageContent) {
        workshopDAO.saveWorkshop(workshop, noteList, storageContent);
    }
}
