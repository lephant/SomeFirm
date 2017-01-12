package ru.lephant.learning.spring.SomeFirmWebFlow.searchcriteries;

import ru.lephant.learning.spring.SomeFirmWebFlow.enums.TeamSearchType;

import java.io.Serializable;

public class TeamSearchCriteria implements Serializable {

    private String thingName;
    private TeamSearchType searchType;


    public TeamSearchType getSearchType() {
        return searchType;
    }

    public void setSearchType(TeamSearchType searchType) {
        this.searchType = searchType;
    }

    public String getThingName() {
        return thingName;
    }

    public void setThingName(String thingName) {
        this.thingName = thingName;
    }
}
