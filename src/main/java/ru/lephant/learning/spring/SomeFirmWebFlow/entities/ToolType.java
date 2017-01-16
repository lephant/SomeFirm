package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "tool_type", schema = "somefirmdb")
@PrimaryKeyJoinColumn(name = "pressmark")
public class ToolType extends Thing implements Serializable {

    @Size(max = 4096, message = "Слишком длинное описание!")
    private String description;

    @Basic
    @Column(name = "description", nullable = true, length = 4096)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToolType toolType = (ToolType) o;

        return description.equals(toolType.description);
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(getPressmark());
    }
}
