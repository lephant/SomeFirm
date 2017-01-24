package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Workshop implements Serializable {

    private long id;

    @Size(min = 1, max = 255, message = "Название должно быть указано и не может быть больше 255 символов!")
    private String name;

    private List<StorageContent> contents = new ArrayList<StorageContent>();


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
        return name != null ? name.equals(workshop.name) : workshop.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
