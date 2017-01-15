package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_type", schema = "somefirmdb")
@PrimaryKeyJoinColumn(name = "pressmark")
public class ProductType extends Thing implements Serializable {

    @Size(max = 4096, message = "Слишком длинное описание!")
    private String description;

    @NotNull(message = "Стоимость должна быть указана!")
    @Min(value = 0, message = "Стоимость должна быть положительной!")
    @Digits(integer = 10, fraction = 2, message = "Стоимость должна быть десятизначным числом с максимум 2 знаками после запятой!")
    private BigDecimal cost;

    private List<ProductTypeOperation> operations = new ArrayList<ProductTypeOperation>();


    @Basic
    @Column(name = "description", nullable = true, length = 4096)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "cost", nullable = false, precision = 2)
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @OneToMany(mappedBy = "productType", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<ProductTypeOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<ProductTypeOperation> operations) {
        this.operations = operations;
    }

    public void addOperation(ProductTypeOperation productTypeOperation) {
        operations.add(productTypeOperation);
    }

    public void removeOperation(ProductTypeOperation productTypeOperation) {
        if (operations.contains(productTypeOperation))
            operations.remove(productTypeOperation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductType that = (ProductType) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return cost.equals(that.cost);

    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + cost.hashCode();
        return result;
    }
}
