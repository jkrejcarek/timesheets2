package timesheets2;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import timesheets2.beans.TimesheetService;
import timesheets2.model.Timesheet;

@Named(value="timesheetsService")
@RequestScoped
public class TimesheetsServiceBean {

	@Inject TimesheetService service;
	
	public List<Timesheet> getTimesheets() {
		List<Timesheet> result = service.getLastTimesheets();
		
		return result;
	}
	
}
