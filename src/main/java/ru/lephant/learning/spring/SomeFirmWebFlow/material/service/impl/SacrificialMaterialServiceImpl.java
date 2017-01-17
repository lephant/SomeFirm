package ru.lephant.learning.spring.SomeFirmWebFlow.material.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.SacrificialMaterialType;
import ru.lephant.learning.spring.SomeFirmWebFlow.material.dao.SacrificialMaterialDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.material.service.SacrificialMaterialService;

import java.util.List;

@Service("sacrificialMaterialService")
public class SacrificialMaterialServiceImpl implements SacrificialMaterialService {

    @Autowired
    SacrificialMaterialDAO sacrificialMaterialDAO;

    @Transactional(readOnly = true)
    public SacrificialMaterialType getSacrificialMaterialByPressmark(long pressmark) {
        return sacrificialMaterialDAO.getSacrificialMaterialByPressmark(pressmark);
    }

    @Transactional(readOnly = true)
    public List listSacrificialMaterial() {
        return sacrificialMaterialDAO.listSacrificialMaterial();
    }

    @Transactional
    public void saveSacrificialMaterial(SacrificialMaterialType sacrificialMaterial, MessageContext messageContext,
                                        boolean editable) {
        try {
            if (editable) {
                sacrificialMaterialDAO.createSacrificialMaterial(sacrificialMaterial);
                addCreateMessage(sacrificialMaterial, messageContext, new MessageBuilder());
            } else {
                sacrificialMaterialDAO.updateSacrificialMaterial(sacrificialMaterial);
                addUpdateMessage(sacrificialMaterial, messageContext, new MessageBuilder());
            }
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            addPressmarkNotAvailableMessage(messageContext, new MessageBuilder());
        } catch (Exception e) {
            e.printStackTrace();
            addErrorMessage(messageContext, new MessageBuilder());
        }
    }

    @Transactional
    public void deleteSacrificialMaterial(long pressmark, MessageContext messageContext) {
        try {
            sacrificialMaterialDAO.deleteSacrificialMaterial(pressmark);
            addDeleteMessage(messageContext, new MessageBuilder());
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
            addUsedMaterialMessage(messageContext, new MessageBuilder());
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

    private void addErrorMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("Материал не был создан из-за ошибки!")
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

    private void addUsedMaterialMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("Материал не был удален, так как он используется!")
                        .build()
                );
    }

    private void addDeleteMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Материал был удален!")
                        .build()
                );
    }
}
