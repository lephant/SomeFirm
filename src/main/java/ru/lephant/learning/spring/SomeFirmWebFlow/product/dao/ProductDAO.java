package ru.lephant.learning.spring.SomeFirmWebFlow.product.dao;

import org.hibernate.exception.ConstraintViolationException;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ProductType;
import java.util.List;

public interface ProductDAO {

    public ProductType getProductByPressmark(long pressmark);

    public List listProduct();

    public void createProduct(ProductType product)
            throws ConstraintViolationException;

    public void updateProduct(ProductType product)
            throws ConstraintViolationException;

    public void deleteProduct(long pressmark)
            throws ConstraintViolationException;

}
