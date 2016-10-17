package timesheets2.model;

import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class TimesheetConverter implements Converter {

	@Inject private TimesheetService service;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		 if (value == null || value.isEmpty()) {
	            return null;
	        }

	        try {
	            Integer id = Integer.parseInt(value);
	            Timesheet t = service.getTimesheet(id);
	            return t;
	        } catch (NumberFormatException e) {
	            throw new ConverterException("The value is not a valid Timesheet ID: " + value, e);
	        }
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
            return "";
        }

        if (value instanceof Timesheet) {
            Integer id = ((Timesheet) value).getId();
            return (id != null) ? String.valueOf(id) : null;
        } else {
            throw new ConverterException("The value is not a valid Timesheet instance: " + value);
        }
	}

}
