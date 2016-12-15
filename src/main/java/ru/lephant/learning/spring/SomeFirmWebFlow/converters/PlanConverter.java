package ru.lephant.learning.spring.SomeFirmWebFlow.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.lephant.learning.spring.SomeFirmWebFlow.entities.Plan;
import ru.lephant.learning.spring.SomeFirmWebFlow.plan.service.PlanService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Controller(value = "planConverterController")
@FacesConverter(value = "planConverter")
public class PlanConverter implements Converter {

    @Autowired
    PlanService planService;

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Plan plan = planService.getPlanById(Long.valueOf(s));
        return plan;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return o.toString();
    }

}
