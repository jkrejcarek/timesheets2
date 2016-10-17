package timesheets2.beans;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import timesheets2.model.Timesheet;
import timesheets2.model.TimesheetService;

@Named(value="timesheetsbean")
@RequestScoped
public class TimesheetsBean {
	private String id;


	@Inject TimesheetService service;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		System.out.println("Setting id: " + id);
		this.id = id;
	}
	
	public void onload() {
		if (id != null && id.length() > 0) {
			System.out.println("Loading vydaj with id" + id);
			Timesheet ts = service.getTimesheet(Integer.parseInt(id));
			
			if (ts != null) {
				Map<String, Object> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
				requestMap.put("timesheet", ts);
				System.out.println("Set in request map: " + ts.toString());
			}
		}
	}
}
