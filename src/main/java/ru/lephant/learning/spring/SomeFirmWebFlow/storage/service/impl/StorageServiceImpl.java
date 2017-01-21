package ru.lephant.learning.spring.SomeFirmWebFlow.storage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Service;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageContent;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.StorageJournal;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Thing;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.JournalOperationType;
import ru.lephant.learning.spring.SomeFirmWebFlow.exceptions.InsufficientNumberOfItemsException;
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


    @Override
    public void writeOffThingFromWorkshop(StorageJournal note,
                                          ArrayList<StorageJournal> noteList,
                                          MessageContext messageContext) {
        try {
            StorageContent currentContentInWorkshop = note.getWorkshop().getContentByThing(note.getThing());

            if (currentContentInWorkshop == null || currentContentInWorkshop.getCount() < note.getCount()) {
                throw new InsufficientNumberOfItemsException("Недостаточное количество предметов в цеху.");
            }

            currentContentInWorkshop.setCount(currentContentInWorkshop.getCount() - note.getCount());

            note.setDateAndTime(new Date().getTime());
            note.setJournalOperationType(JournalOperationType.WRITE_OFF_FROM_WORKSHOP_OPERATION);

            noteList.add(note);

            addWriteOffThingFromWorkshopInfoMessage(messageContext, new MessageBuilder());
        } catch (InsufficientNumberOfItemsException e) {
            addWriteOffThingFromWorkshopErrorMessage(messageContext, new MessageBuilder());
        }
    }


    @Override
    public void writeOffThingFromStorage(StorageJournal note,
                                         ArrayList<StorageJournal> noteList,
                                         ArrayList<StorageContent> storageContent,
                                         MessageContext messageContext) {
        try {
            StorageContent currentContentInStorage = getContentOfStorageByThing(storageContent, note.getThing());

            if (currentContentInStorage == null || currentContentInStorage.getCount() < note.getCount()) {
                throw new InsufficientNumberOfItemsException("Недостаточное количество предметов на складе.");
            }

            currentContentInStorage.setCount(currentContentInStorage.getCount() - note.getCount());

            note.setDateAndTime(new Date().getTime());
            note.setJournalOperationType(JournalOperationType.WRITE_OFF_FROM_STORAGE_OPERATION);

            noteList.add(note);

            addWriteOffThingFromStorageInfoMessage(messageContext, new MessageBuilder());
        } catch (InsufficientNumberOfItemsException e) {
            addWriteOffThingFromStorageErrorMessage(messageContext, new MessageBuilder());
        }
    }


    @Override
    public void sendThingFromWorkshopToStorage(StorageJournal note,
                                               ArrayList<StorageJournal> noteList,
                                               ArrayList<StorageContent> storageContent,
                                               MessageContext messageContext) {
        try {
            StorageContent currentContentInWorkshop = note.getWorkshop().getContentByThing(note.getThing());

            if (currentContentInWorkshop == null || currentContentInWorkshop.getCount() < note.getCount()) {
                throw new InsufficientNumberOfItemsException("Недостаточное количество предметов в цеху.");
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

            addSendThingFromWorkshopToStorageInfoMessage(messageContext, new MessageBuilder());
        } catch (InsufficientNumberOfItemsException e) {
            addSendThingFromWorkshopToStorageErrorMessage(messageContext, new MessageBuilder());
        }
    }


    @Override
    public void sendThingFromStorageToWorkshop(StorageJournal note,
                                               ArrayList<StorageJournal> noteList,
                                               ArrayList<StorageContent> storageContent,
                                               ArrayList<StorageContent> contentsOfWorkshops,
                                               MessageContext messageContext) {
        try {
            StorageContent currentContentInStorage = getContentOfStorageByThing(storageContent, note.getThing());

            if (currentContentInStorage == null || currentContentInStorage.getCount() < note.getCount()) {
                throw new InsufficientNumberOfItemsException("Недостаточное количество предметов на складе.");
            }

            StorageContent currentContentInWorkshop = getContentOfStorageByThingAndWorkshop(contentsOfWorkshops,
                    note.getThing(), note.getWorkshop());
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

            addSendThingFromStorageToWorkshopInfoMessage(messageContext, new MessageBuilder());
        } catch (InsufficientNumberOfItemsException e) {
            addSendThingFromStorageToWorkshopErrorMessage(messageContext, new MessageBuilder());
        }
    }

    @Override
    public void importThingToStorage(StorageJournal note,
                                     ArrayList<StorageJournal> noteList,
                                     ArrayList<StorageContent> storageContent,
                                     MessageContext messageContext) {
        StorageContent currentContentInStorage = getContentOfStorageByThing(storageContent, note.getThing());

        if (currentContentInStorage == null) {
            currentContentInStorage = createContentByNote(note);
            storageContent.add(currentContentInStorage);
        }

        currentContentInStorage.setCount(currentContentInStorage.getCount() + note.getCount());

        note.setDateAndTime(new Date().getTime());
        note.setJournalOperationType(JournalOperationType.IMPORT_OPERATION);

        noteList.add(note);

        addImportThingToStorage(messageContext, new MessageBuilder());
    }


    @Override
    public List listStorageContent() {
        return storageDAO.listStorageContent();
    }


    @Override
    public StorageContent getStorageContentByThing(Thing thing) {
        return storageDAO.getStorageContentByThing(thing);
    }

    @Override
    public void commitStorage(ArrayList<StorageContent> storageContent,
                              ArrayList<StorageJournal> noteList,
                              ArrayList<StorageContent> changedWorkshopContents,
                              MessageContext messageContext) {
        storageDAO.commitStorage(storageContent, noteList, changedWorkshopContents);
        addCommitMessage(messageContext, new MessageBuilder());
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

    private StorageContent getContentOfStorageByThingAndWorkshop(ArrayList<StorageContent> contentsOfWorkshops, Thing thing, Workshop workshop) {
        StorageContent contentInStorage = null;
        for (StorageContent content : contentsOfWorkshops) {
            if (content.getThing().equals(thing) && content.getWorkshop().equals(workshop)) {
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

    private void addWriteOffThingFromWorkshopInfoMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Списание из цеха совершится после сохранения!")
                        .build()
                );
    }


    private void addWriteOffThingFromWorkshopErrorMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("В цеху нет столько таких предметов!")
                        .build()
                );
    }

    private void addWriteOffThingFromStorageInfoMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Списание со склада совершится после сохранения!")
                        .build()
                );
    }


    private void addWriteOffThingFromStorageErrorMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("На складе нет столько таких предметов!")
                        .build()
                );
    }

    private void addSendThingFromWorkshopToStorageInfoMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Отправка из цеха на склад совершится после сохранения!")
                        .build()
                );
    }


    private void addSendThingFromWorkshopToStorageErrorMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("В цеху нет столько таких предметов!")
                        .build()
                );
    }

    private void addSendThingFromStorageToWorkshopInfoMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Отправка со склада в цех совершится после сохранения!")
                        .build()
                );
    }


    private void addSendThingFromStorageToWorkshopErrorMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("На складе нет столько таких предметов!")
                        .build()
                );
    }

    private void addImportThingToStorage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Импорт на склад предметов зафиксируется после сохранения!")
                        .build()
                );
    }


    private void addCommitMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Все изменения сохранены!")
                        .build()
                );
    }

}
