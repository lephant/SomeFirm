package ru.lephant.learning.spring.SomeFirmWebFlow.enums;

public enum JournalOperationType {

    IMPORT_OPERATION("Закупка"),
    WRITE_OFF_FROM_WORKSHOP_OPERATION("Списание из цеха"),
    WRITE_OFF_FROM_STORAGE_OPERATION("Списание со склада"),
    SEND_FROM_STORAGE_TO_WORKSHOP_OPERATION("Отправка в цех со склада"),
    SEND_FROM_WORKSHOP_TO_STORAGE_OPERATION("Отправка на склад из цеха");


    private String typeDescription;


    JournalOperationType(String typeDescription) {
        this.typeDescription = typeDescription;
    }


    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

}