package ru.lephant.learning.spring.SomeFirmWebFlow.reference;

import org.springframework.stereotype.Component;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.JournalOperationType;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.OrderState;
import ru.lephant.learning.spring.SomeFirmWebFlow.enums.TeamSearchType;

@Component("referenceData")
public class ReferenceData {

    public static class JournalOperationTypes {
        public static JournalOperationType importOperation = new JournalOperationType(1, "Закупка");
        public static JournalOperationType writeOffFromWorkshopOperation = new JournalOperationType(2, "Списание из цеха");
        public static JournalOperationType writeOffFromStorageOperation = new JournalOperationType(3, "Списание со склада");
        public static JournalOperationType sendFromStorageToWorkshopOperation = new JournalOperationType(4, "Отправка в цех со склада");
        public static JournalOperationType sendFromWorkshopToStorageOperation = new JournalOperationType(5, "Отправка на склад из цеха");
    }

    public OrderState[] getOrderStates() {
        return OrderState.values();
    }

    public TeamSearchType[] getTeamSearchTypes() {
        return TeamSearchType.values();
    }

}
