package ru.lephant.learning.spring.SomeFirmWebFlow.product.service;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ProductType;
import java.util.List;

public interface ProductService {

    public ProductType getProductByPressmark(long pressmark);

    public List listProduct();

    public void saveProduct(ProductType product);

    public void deleteProduct(long pressmark);

}
