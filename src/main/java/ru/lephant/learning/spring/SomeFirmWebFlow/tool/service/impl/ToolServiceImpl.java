package ru.lephant.learning.spring.SomeFirmWebFlow.tool.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Service;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ToolType;
import ru.lephant.learning.spring.SomeFirmWebFlow.tool.dao.ToolDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.tool.service.ToolService;

import java.util.List;

@Service(value = "toolService")
public class ToolServiceImpl implements ToolService {

    @Autowired
    ToolDAO toolDAO;


    public ToolType getToolByPressmark(long pressmark) {
        return toolDAO.getToolByPressmark(pressmark);
    }

    public List listTool() {
        return toolDAO.listTool();
    }

    public boolean saveTool(ToolType tool, MessageContext messageContext, boolean isNew) {
        try {
            if (isNew) {
                toolDAO.createTool(tool);
                addCreateMessage(messageContext, new MessageBuilder());
                return true;
            } else {
                toolDAO.updateTool(tool);
                addUpdateMessage(messageContext, new MessageBuilder());
                return true;
            }
        } catch (ConstraintViolationException e) {
            //TODO: сделать логгер
            addPressmarkNotAvailableMessage(messageContext, new MessageBuilder());
            return false;
        }
    }

    public void deleteTool(long pressmark, MessageContext messageContext) {
        try {
            toolDAO.deleteTool(pressmark);
            addDeleteMessage(messageContext, new MessageBuilder());
        } catch (ConstraintViolationException e) {
            // TODO: сделать логгер
            addMaterialIsUsedMessage(messageContext, new MessageBuilder());
        }
    }

    private void addCreateMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Инструмент успешно создан!")
                        .build()
                );
    }

    private void addUpdateMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Инструмент успешно обновлен!")
                        .build()
                );
    }

    private void addPressmarkNotAvailableMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("Материал не был создан, так как этот шифр уже занят!")
                        .build()
                );
    }

    private void addDeleteMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Инструмент успешно удален!")
                        .build()
                );
    }

    private void addMaterialIsUsedMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("Инструмент не может быть удален, так как он используется!")
                        .build()
                );
    }

}
