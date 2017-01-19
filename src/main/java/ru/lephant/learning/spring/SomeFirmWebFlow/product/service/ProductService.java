package ru.lephant.learning.spring.SomeFirmWebFlow.product.service;

import org.springframework.binding.message.MessageContext;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ProductType;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ProductTypeOperation;

import java.util.List;

public interface ProductService {

    public ProductType getProductByPressmark(long pressmark);

    public List listProduct();

    public boolean saveProduct(ProductType product, MessageContext messageContext, boolean isNew);

    public boolean deleteProduct(long pressmark, MessageContext messageContext);

    public void addOperationToList(ProductType product, ProductTypeOperation operation, MessageContext messageContext);

    public void removeOperationFromList(ProductType product, ProductTypeOperation operation, MessageContext messageContext);

}
