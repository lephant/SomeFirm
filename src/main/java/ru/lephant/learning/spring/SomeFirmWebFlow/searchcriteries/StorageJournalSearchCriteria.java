package ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Workshop;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.JournalOperationType;

import java.io.Serializable;
import java.util.Date;

public class StorageJournalSearchCriteria implements Serializable {

    private Workshop workshop;
    private boolean searchByDate;
    private long minTime;
    private long maxTime;
    private JournalOperationType operationType;


    public StorageJournalSearchCriteria() {
        long currentDate = new Date().getTime();
        this.minTime = currentDate;
        this.maxTime = currentDate;
    }


    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public boolean isSearchByDate() {
        return searchByDate;
    }

    public void setSearchByDate(boolean searchByDate) {
        this.searchByDate = searchByDate;
    }

    public long getMinTime() {
        return minTime;
    }

    public void setMinTime(long minTime) {
        this.minTime = minTime;
    }

    public long getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(long maxTime) {
        this.maxTime = maxTime;
    }

    public JournalOperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(JournalOperationType operationType) {
        this.operationType = operationType;
    }
}
