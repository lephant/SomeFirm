package ru.lephant.learning.spring.SomeFirmWebFlow.enums;

public enum OrderState {

    NOT_VIEWED("Не просмотрено"),
    VIEWED("Просмотрено"),
    IS_DENIED("Отказано"),
    DURING("В процессе"),
    DONE("Завершено");

    private String stateDescription;

    OrderState(String stateDescription) {
        this.stateDescription = stateDescription;
    }

    public String getStateDescription() {
        return stateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription;
    }

}
