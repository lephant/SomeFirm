package ru.lephant.learning.spring.SomeFirmWebFlow.reference;

import org.springframework.stereotype.Component;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.JournalOperationType;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.OrderState;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.TeamSearchType;

@Component("referenceData")
public class ReferenceData {

    public JournalOperationType[] getJournalOperationTypes() {
        return JournalOperationType.values();
    }

    public OrderState[] getOrderStates() {
        return OrderState.values();
    }

    public TeamSearchType[] getTeamSearchTypes() {
        return TeamSearchType.values();
    }

}