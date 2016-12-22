package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product_type", schema = "somefirmdb")
public class ProductType implements Serializable {
    private long pressmark;
    private String name;
    private String description;
    private BigDecimal cost;
    private List<ProductTypeOperation> operations;
    private Pressmarks pressmarks;

    @Id
    @Column(name = "pressmark", nullable = false)
    public long getPressmark() {
        return pressmark;
    }

    public void setPressmark(long pressmark) {
        this.pressmark = pressmark;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    @OneToOne
    @JoinColumn(name = "pressmark", referencedColumnName = "pressmark")
    public Pressmarks getPressmarks() {
        return pressmarks;
    }

    public void setPressmarks(Pressmarks pressmarks) {
        this.pressmarks = pressmarks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductType that = (ProductType) o;

        if (pressmark != that.pressmark) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (pressmark ^ (pressmark >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}
