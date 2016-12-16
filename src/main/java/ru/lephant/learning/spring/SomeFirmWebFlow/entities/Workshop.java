package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Workshop implements Serializable {
    private long id;
    private String name;
    private Set<StoragesContent> contents;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "workshop", fetch = FetchType.LAZY)
    public Set<StoragesContent> getContents() {
        return contents;
    }

    public void setContents(Set<StoragesContent> contents) {
        this.contents = contents;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workshop workshop = (Workshop) o;

        return id == workshop.id;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
