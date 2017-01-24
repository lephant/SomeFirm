package ru.lephant.learning.spring.SomeFirmWebFlow.operation.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Service;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Operation;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.OperationSacrificialMaterial;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.OperationTool;
import ru.lephant.learning.spring.SomeFirmWebFlow.operation.FileUploadBean;
import ru.lephant.learning.spring.SomeFirmWebFlow.operation.dao.OperationDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.operation.service.OperationService;

import java.io.Serializable;
import java.util.List;

@Service("operationService")
public class OperationServiceImpl implements OperationService, Serializable {

    @Autowired
    private OperationDAO operationDAO;


    @Override
    public Operation getOperationById(long id) {
        return operationDAO.getOperationById(id);
    }

    @Override
    public Operation getLazyOperationById(long id) {
        return operationDAO.getLazyOperationById(id);
    }

    @Override
    public byte[] getPlanOfOperationById(long id) {
        return operationDAO.getPlanOfOperationById(id);
    }

    @Override
    public List listOperation() {
        return operationDAO.listOperation();
    }

    @Override
    public boolean deleteOperation(long id, MessageContext messageContext) {
        try {
            operationDAO.deleteOperation(id);
            addDeleteMessage(messageContext, new MessageBuilder());
            return true;
        } catch (ConstraintViolationException e) {
            //TODO: сделать логгер
            addOperationIsUsedMessage(messageContext, new MessageBuilder());
            return false;
        }
    }

    @Override
    public void saveOperation(Operation operation, FileUploadBean fileUploadBean, MessageContext messageContext) {
        operationDAO.saveOperation(operation, fileUploadBean);
        addSaveMessage(messageContext, new MessageBuilder());
    }

    @Override
    public void addToolToList(Operation operation, OperationTool tool,
                              MessageContext messageContext) {
        operation.addTool(tool);
        addMessageWhenAddingTool(messageContext, new MessageBuilder());
    }

    @Override
    public void removeToolFromList(Operation operation, OperationTool tool,
                                   MessageContext messageContext) {
        operation.removeTool(tool);
        addMessageWhenRemovingTool(messageContext, new MessageBuilder());
    }

    @Override
    public void addMaterialToList(Operation operation, OperationSacrificialMaterial material,
                                  MessageContext messageContext) {
        operation.addSacrificialMaterial(material);
        addMessageWhenAddingMaterial(messageContext, new MessageBuilder());
    }

    @Override
    public void removeMaterialFromList(Operation operation, OperationSacrificialMaterial material,
                                       MessageContext messageContext) {
        operation.removeSacrificialMaterial(material);
        addMessageWhenRemovingMaterial(messageContext, new MessageBuilder());
    }


    private void addDeleteMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Операция успешно удалена!")
                        .build()
                );
    }

    private void addOperationIsUsedMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("Операция не может быть удалена, так как она используется!")
                        .build()
                );
    }

    private void addSaveMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Операция успешно сохранена!")
                        .build()
                );
    }

    private void addMessageWhenAddingTool(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Инструменты будут добавлены после сохранения.")
                        .build()
                );
    }

    private void addMessageWhenRemovingTool(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Инструменты будут удалены после сохранения.")
                        .build()
                );
    }

    private void addMessageWhenAddingMaterial(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Расходуемые материалы будут добавлены после сохранения.")
                        .build()
                );
    }

    private void addMessageWhenRemovingMaterial(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Расходуемые материалы будут удалены после сохранения.")
                        .build()
                );
    }

}
