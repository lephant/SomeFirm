package ru.lephant.learning.spring.SomeFirmWebFlow.workshop.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Service;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageContent;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;
import ru.lephant.learning.spring.SomeFirmWebFlow.workshop.dao.WorkshopDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.workshop.service.WorkshopService;

import java.util.ArrayList;
import java.util.List;

@Service("workshopService")
public class WorkshopServiceImpl implements WorkshopService {

    @Autowired
    WorkshopDAO workshopDAO;


    @Override
    public List listWorkshop() {
        return workshopDAO.listWorkshop();
    }

    @Override
    public List listWorkshopWithAbstractMainStorage() {
        return workshopDAO.listWorkshopWithAbstractMainStorage();
    }

    @Override
    public Workshop getWorkshopById(long id) {
        return workshopDAO.getWorkshopById(id);
    }

    @Override
    public Workshop getLazyWorkshopById(long id) {
        return workshopDAO.getLazyWorkshopById(id);
    }

    @Override
    public boolean deleteWorkshop(long id, MessageContext messageContext) {
        try {
            workshopDAO.deleteWorkshop(id);
            addDeleteMessage(messageContext, new MessageBuilder());
            return true;
        } catch (ConstraintViolationException e) {
            addWorkshopIsUsedMessage(messageContext, new MessageBuilder());
            return false;
        }
    }

    @Override
    public void saveWorkshop(Workshop workshop,
                             ArrayList<StorageJournal> noteList,
                             ArrayList<StorageContent> storageContent,
                             MessageContext messageContext) {
        workshopDAO.saveWorkshop(workshop, noteList, storageContent);
        addSaveMessage(messageContext, new MessageBuilder());
    }


    private void addDeleteMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Цех успешно удален!")
                        .build()
                );
    }

    private void addWorkshopIsUsedMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("Цех не может быть удален, так как он используется!")
                        .build()
                );
    }

    private void addSaveMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Цех успешно сохранен!")
                        .build()
                );
    }
}
