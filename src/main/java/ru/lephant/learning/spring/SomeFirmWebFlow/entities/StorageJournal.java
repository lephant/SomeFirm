package ru.lephant.learning.spring.SomeFirmWebFlow.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "storage_journal", schema = "somefirmdb")
public class StorageJournal implements Serializable {
    private long id;
    private Workshop workshop;
    private Thing thing;
    private int count;
    private long dateAndTime;
    private StorageEmploye storageEmploye;
    private String description;
    private JournalOperationType journalOperationType;

    public StorageJournal() {
    }

    public StorageJournal(Workshop workshop, Thing thing) {
        this.workshop = workshop;
        this.thing = thing;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "workshop_id", referencedColumnName = "id")
    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "pressmark", referencedColumnName = "pressmark")
    public Thing getThing() {
        return thing;
    }

    public void setThing(Thing thing) {
        this.thing = thing;
    }

    @Basic
    @Column(name = "count", nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Basic
    @Column(name = "date_and_time", nullable = false)
    public long getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(long dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_employe_id", referencedColumnName = "id")
    public StorageEmploye getStorageEmploye() {
        return storageEmploye;
    }

    public void setStorageEmploye(StorageEmploye storageEmployeId) {
        this.storageEmploye = storageEmployeId;
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "description", nullable = true, length = 4096)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "journal_operation_type", referencedColumnName = "id")
    public JournalOperationType getJournalOperationType() {
        return journalOperationType;
    }

    public void setJournalOperationType(JournalOperationType journalOperationType) {
        this.journalOperationType = journalOperationType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StorageJournal that = (StorageJournal) o;

        if (id != that.id) return false;
        if (count != that.count) return false;
        if (dateAndTime != that.dateAndTime) return false;
        if (!thing.equals(that.thing)) return false;
        if (!storageEmploye.equals(that.storageEmploye)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return journalOperationType.equals(that.journalOperationType);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + thing.hashCode();
        result = 31 * result + count;
        result = 31 * result + (int) (dateAndTime ^ (dateAndTime >>> 32));
        result = 31 * result + storageEmploye.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + journalOperationType.hashCode();
        return result;
    }
}
