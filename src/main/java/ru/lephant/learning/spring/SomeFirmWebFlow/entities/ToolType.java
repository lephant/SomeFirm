package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;

@Entity
@Table(name = "tool_type", schema = "somefirmdb")
public class ToolType {
    private long pressmark;
    private String name;
    private String description;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ToolType toolType = (ToolType) o;

        if (pressmark != toolType.pressmark) return false;
        if (name != null ? !name.equals(toolType.name) : toolType.name != null) return false;
        if (description != null ? !description.equals(toolType.description) : toolType.description != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (pressmark ^ (pressmark >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
