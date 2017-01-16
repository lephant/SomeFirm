package ru.lephant.learning.spring.SomeFirmWebFlow.validators;

import org.primefaces.model.DualListModel;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "workersValidator")
public class WorkersPickListValidator implements Validator{

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        DualListModel list = (DualListModel) o;
        if (list.getTarget().size() == 0) {
            FacesMessage message = new FacesMessage("Не выбран ни 1 рабочий!");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}
