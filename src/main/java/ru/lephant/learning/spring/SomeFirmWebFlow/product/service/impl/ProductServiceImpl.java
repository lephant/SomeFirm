package ru.lephant.learning.spring.SomeFirmWebFlow.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.ProductType;
import ru.lephant.learning.spring.SomeFirmWebFlow.product.dao.ProductDAO;
import ru.lephant.learning.spring.SomeFirmWebFlow.product.service.ProductService;
import java.util.List;

@Service(value = "productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    @Transactional(readOnly = true)
    public ProductType getProductByPressmark(long pressmark) {
        return productDAO.getProductByPressmark(pressmark);
    }

    @Transactional(readOnly = true)
    public List listProduct() {
        return productDAO.listProduct();
    }

    @Transactional
    public void saveProduct(ProductType product) {
        productDAO.saveProduct(product);
    }

    @Transactional
    public void deleteProduct(long pressmark) {
        productDAO.deleteProduct(pressmark);
    }

}
