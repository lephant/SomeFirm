package ru.lephant.learning.spring.SomeFirmWebFlow.converters;

import org.springframework.stereotype.Component;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component("dateConverterController")
@FacesConverter(value = "dateConverter")
public class DateConverter implements Converter {

    private static final String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";

    @Override
    public Long getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        try {
            DateFormat format = new SimpleDateFormat(DATE_PATTERN);
            Date date = format.parse(s);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        Date date = new Date(Long.valueOf(o.toString()));
        Format format = new SimpleDateFormat(DATE_PATTERN);
        return format.format(date);
    }
}
