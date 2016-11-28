package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;

@Entity
@Table(name = "operation_tool", schema = "somefirmdb")
public class OperationTool {
    private long id;
    private Operation operation;
    private ToolType toolType;
    private int count;

    @Id
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
    @JoinColumn(name = "tool_type_id", referencedColumnName = "pressmark")
    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(ToolType toolTypeId) {
        this.toolType = toolTypeId;
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

        OperationTool that = (OperationTool) o;

        if (id != that.id) return false;
        if (count != that.count) return false;
        if (!operation.equals(that.operation)) return false;
        return toolType.equals(that.toolType);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + toolType.hashCode();
        result = 31 * result + count;
        return result;
    }
}
