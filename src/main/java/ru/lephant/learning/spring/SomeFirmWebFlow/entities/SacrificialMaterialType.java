package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sacrificial_material_type", schema = "somefirmdb")
public class SacrificialMaterialType implements Serializable {
    private long pressmark;
    private String name;
    private String description;
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

        SacrificialMaterialType that = (SacrificialMaterialType) o;

        if (pressmark != that.pressmark) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (pressmark ^ (pressmark >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(pressmark);
    }
}
