package ru.lephant.learning.spring.SomeFirmWebFlow.product.dao;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ProductType;
import java.util.List;

public interface ProductDAO {

    public ProductType getProductByPressmark(long pressmark);

    public List listProduct();

    public void saveProduct(ProductType product);

    public void deleteProduct(long pressmark);

}
