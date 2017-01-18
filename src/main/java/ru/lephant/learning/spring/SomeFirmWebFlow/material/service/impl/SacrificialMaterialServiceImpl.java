package ru.lephant.learning.spring.SomeFirmWebFlow.material.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Service;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.SacrificialMaterialType;
import ru.lephant.learning.spring.SomeFirmWebFlow.material.dao.SacrificialMaterialDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.material.service.SacrificialMaterialService;

import java.util.List;

@Service("sacrificialMaterialService")
public class SacrificialMaterialServiceImpl implements SacrificialMaterialService {

    @Autowired
    SacrificialMaterialDAO sacrificialMaterialDAO;


    @Override
    public SacrificialMaterialType getSacrificialMaterialByPressmark(long pressmark) {
        return sacrificialMaterialDAO.getSacrificialMaterialByPressmark(pressmark);
    }

    @Override
    public List listSacrificialMaterial() {
        return sacrificialMaterialDAO.listSacrificialMaterial();
    }

    @Override
    public boolean saveSacrificialMaterial(SacrificialMaterialType sacrificialMaterial, MessageContext messageContext,
                                           boolean isNew) {
        try {
            if (isNew) {
                sacrificialMaterialDAO.createSacrificialMaterial(sacrificialMaterial);
                addCreateMessage(sacrificialMaterial, messageContext, new MessageBuilder());
                return true;
            } else {
                sacrificialMaterialDAO.updateSacrificialMaterial(sacrificialMaterial);
                addUpdateMessage(sacrificialMaterial, messageContext, new MessageBuilder());
                return true;
            }
        } catch (ConstraintViolationException e) {
            // TODO: Сделать логгер
            addPressmarkNotAvailableMessage(messageContext, new MessageBuilder());
            return false;
        }
    }

    @Override
    public void deleteSacrificialMaterial(long pressmark, MessageContext messageContext) {
        try {
            sacrificialMaterialDAO.deleteSacrificialMaterial(pressmark);
            addDeleteMessage(messageContext, new MessageBuilder());
        } catch (ConstraintViolationException e) {
            // TODO: Сделать логгер
            addMaterialIsUsedMessage(messageContext, new MessageBuilder());
        }
    }

    private void addCreateMessage(SacrificialMaterialType sacrificialMaterial, MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Материал '" + sacrificialMaterial.getName() + "' успешно создан!")
                        .build()
                );
    }

    private void addUpdateMessage(SacrificialMaterialType sacrificialMaterial, MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Материал '" + sacrificialMaterial.getName() + "' успешно обновлен!")
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

    private void addMaterialIsUsedMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("Материал не может быть удален, так как он используется!")
                        .build()
                );
    }

    private void addDeleteMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Материал успешно удален!")
                        .build()
                );
    }
}
