package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "operation_sacrificial_material", schema = "somefirmdb")
public class OperationSacrificialMaterial implements Serializable {

    private long id;

    @NotNull(message = "Не задана операция. Ошибка!")
    private Operation operation;

    @NotNull(message = "Не задан расходуемый материал!")
    private SacrificialMaterialType sacrificialMaterial;

    @NotNull(message = "Указание количества - обязательно!")
    @Min(value = 1, message = "Количество может быть только положительным целым числом!")
    private int count;


    public OperationSacrificialMaterial() { }

    public OperationSacrificialMaterial(Operation operation) {
        this.operation = operation;
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
    @JoinColumn(name = "operation_id", referencedColumnName = "id")
    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operationId) {
        this.operation = operationId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sacrificial_material_id", referencedColumnName = "pressmark")
    public SacrificialMaterialType getSacrificialMaterial() {
        return sacrificialMaterial;
    }

    public void setSacrificialMaterial(SacrificialMaterialType sacrificialMaterialId) {
        this.sacrificialMaterial = sacrificialMaterialId;
    }

    @Basic
    @Column(name = "count", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationSacrificialMaterial that = (OperationSacrificialMaterial) o;

        if (id != that.id) return false;
        if (count != that.count) return false;
        if (!operation.equals(that.operation)) return false;
        return sacrificialMaterial.equals(that.sacrificialMaterial);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + sacrificialMaterial.hashCode();
        result = 31 * result + count;
        return result;
    }
}
