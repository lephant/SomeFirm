package ru.lephant.learning.spring.SomeFirmWebFlow.enums;

import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Group;

public enum GroupType {

    USER_GROUP(new Group(1, "users")),
    WORKER_GROUP(new Group(2, "workers")),
    BRIGADIER_GROUP(new Group(3, "brigadiers")),
    MANAGER_GROUP(new Group(4, "managers")),
    STOREKEEPER_GROUP(new Group(5, "storekeepers"));


    private Group group;


    GroupType(Group group) {
        this.group = group;
    }


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
