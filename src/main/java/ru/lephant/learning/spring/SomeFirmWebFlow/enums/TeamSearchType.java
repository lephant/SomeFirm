package ru.lephant.learning.spring.SomeFirmWebFlow.enums;

public enum TeamSearchType {

    FINISHED("Завершенные"),
    ACTUAL("Выполняющиеся"),
    UPCOMING("Будущие");


    private String typeDescription;


    TeamSearchType(String typeDescription) {
        this.typeDescription = typeDescription;
    }


    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }
}