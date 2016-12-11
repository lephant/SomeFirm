package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Pressmarks implements Serializable {
    private long pressmark;
    private int typeId;

    @Id
    @Column(name = "pressmark", nullable = false)
    public long getPressmark() {
        return pressmark;
    }

    public void setPressmark(long pressmark) {
        this.pressmark = pressmark;
    }

    @Basic
    @Column(name = "type_id", nullable = false)
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pressmarks that = (Pressmarks) o;

        if (pressmark != that.pressmark) return false;
        if (typeId != that.typeId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (pressmark ^ (pressmark >>> 32));
        result = 31 * result + typeId;
        return result;
    }
}
