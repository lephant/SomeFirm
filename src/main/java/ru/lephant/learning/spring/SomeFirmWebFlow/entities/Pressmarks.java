package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import ru.lephant.learning.spring.SomeFirmWebFlow.enums.ItemType;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Pressmarks implements Serializable {
    private long pressmark;
    private ItemType type;

    public Pressmarks() {
    }

    public Pressmarks(long pressmark, ItemType type) {
        this.pressmark = pressmark;
        this.type = type;
    }

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
    @Enumerated(value = EnumType.ORDINAL)
    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
