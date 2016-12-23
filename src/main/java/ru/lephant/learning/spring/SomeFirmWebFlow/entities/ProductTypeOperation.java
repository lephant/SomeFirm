package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_type_operation", schema = "somefirmdb")
public class ProductTypeOperation implements Serializable {
    private long id;
    private ProductType productType;
    private Operation operation;

    public ProductTypeOperation(ProductType productType) {
        this.productType = productType;
    }

    public ProductTypeOperation() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pressmark", referencedColumnName = "pressmark")
    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operation_id", referencedColumnName = "id")
    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductTypeOperation that = (ProductTypeOperation) o;

        if (id != that.id) return false;
        if (!productType.equals(that.productType)) return false;
        return operation.equals(that.operation);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + productType.hashCode();
        result = 31 * result + operation.hashCode();
        return result;
    }
}
