package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "storages_content", schema = "somefirmdb")
public class StorageContent implements Serializable {

    private long id;

    private Workshop workshop;

    private Thing thing;

    @NotNull(message = "Введите количество!")
    @Min(value = 0, message = "Количество не может быть отрицательным! Действие не совершено.")
    private int count;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "workshop_id", referencedColumnName = "id")
    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshopId) {
        this.workshop = workshopId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pressmark_id", referencedColumnName = "pressmark")
    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing pressmarkId) {
        this.thing = pressmarkId;
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

        StorageContent that = (StorageContent) o;

        if (id != that.id) return false;
        if (count != that.count) return false;
        if (!workshop.equals(that.workshop)) return false;
        return thing.equals(that.thing);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + workshop.hashCode();
        result = 31 * result + thing.hashCode();
        result = 31 * result + count;
        return result;
    }
}
