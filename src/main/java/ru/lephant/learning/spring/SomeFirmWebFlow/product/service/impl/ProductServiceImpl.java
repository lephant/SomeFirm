package ru.lephant.learning.spring.SomeFirmWebFlow.product.service.impl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Service;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ProductType;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ProductTypeOperation;
import ru.lephant.learning.spring.SomeFirmWebFlow.product.dao.ProductDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.product.service.ProductService;

import java.util.List;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;


    @Override
    public ProductType getProductByPressmark(long pressmark) {
        return productDAO.getProductByPressmark(pressmark);
    }

    @Override
    public List listProduct() {
        return productDAO.listProduct();
    }

    @Override
    public boolean saveProduct(ProductType product, MessageContext messageContext, boolean isNew) {
        try {
            if (isNew) {
                productDAO.createProduct(product);
                addCreateMessage(messageContext, new MessageBuilder());
                return true;
            } else {
                productDAO.updateProduct(product);
                addUpdateMessage(messageContext, new MessageBuilder());
                return true;
            }
        } catch (ConstraintViolationException e) {
            //TODO: сделать логгер
            addPressmarkNotAvailableMessage(messageContext, new MessageBuilder());
            return false;
        }
    }

    @Override
    public boolean deleteProduct(long pressmark, MessageContext messageContext) {
        try {
            productDAO.deleteProduct(pressmark);
            addDeleteMessage(messageContext, new MessageBuilder());
            return true;
        } catch (ConstraintViolationException e) {
            //TODO: сделать логгер
            addMaterialIsUsedMessage(messageContext, new MessageBuilder());
            return false;
        }
    }

    @Override
    public void addOperationToList(ProductType product, ProductTypeOperation operation, MessageContext messageContext) {
        product.addOperation(operation);
        addMessageWhenAddingOperation(messageContext, new MessageBuilder());
    }

    @Override
    public void removeOperationFromList(ProductType product, ProductTypeOperation operation, MessageContext messageContext) {
        product.removeOperation(operation);
        addMessageWhenRemovingOperation(messageContext, new MessageBuilder());
    }


    private void addCreateMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Продукт успешно создан!")
                        .build()
                );
    }

    private void addUpdateMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Продукт успешно обновлен!")
                        .build()
                );
    }

    private void addPressmarkNotAvailableMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("Продукт не был создан, так как этот шифр уже занят!")
                        .build()
                );
    }

    private void addDeleteMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Продукт успешно удален!")
                        .build()
                );
    }

    private void addMaterialIsUsedMessage(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .error()
                        .defaultText("Продукт не может быть удален, так как он используется!")
                        .build()
                );
    }

    private void addMessageWhenAddingOperation(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Операция будет добавлена после сохранения.")
                        .build()
                );
    }

    private void addMessageWhenRemovingOperation(MessageContext messageContext, MessageBuilder builder) {
        messageContext
                .addMessage(builder
                        .info()
                        .defaultText("Операция будет удалена после сохранения.")
                        .build()
                );
    }
}
