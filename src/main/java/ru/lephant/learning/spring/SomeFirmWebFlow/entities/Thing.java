package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import ru.lephant.learning.spring.SomeFirmWebFlow.enums.ItemType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "pressmarks")
@Inheritance(strategy = InheritanceType.JOINED)
public class Thing implements Serializable {

    private long pressmark;

    @NotNull(message = "Название должно быть указано!")
    @Size(min = 1, max = 255, message = "Название должно быть указано и состоять не больше чем из 255 символов!")
    private String name;

    private ItemType type;


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
    @Column(name = "type_id", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Thing that = (Thing) o;

        if (pressmark != that.pressmark) return false;
        if (!name.equals(that.name)) return false;
        return type == that.type;

    }

    @Override
    public int hashCode() {
        int result = (int) (pressmark ^ (pressmark >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(pressmark);
    }
}
