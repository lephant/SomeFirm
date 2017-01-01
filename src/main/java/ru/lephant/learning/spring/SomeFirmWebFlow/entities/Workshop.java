package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Workshop implements Serializable {
    private long id;
    private String name;
    private List<StorageContent> contents;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "workshop", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    public List<StorageContent> getContents() {
        return contents;
    }

    public void setContents(List<StorageContent> contents) {
        this.contents = contents;
    }

    public StorageContent getContentByThing(Thing thing) {
        for (StorageContent content: contents) {
            if (content.getThing().equals(thing)) {
                return content;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Workshop workshop = (Workshop) o;

        if (id != workshop.id) return false;
        return name.equals(workshop.name);
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
