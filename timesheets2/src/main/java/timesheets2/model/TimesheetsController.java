package timesheets2.model;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named(value="controller")
@RequestScoped
public class TimesheetsController {

	private List<Activity> activities;
	private List<Project> projects;
	
	private Timesheet timesheet;
	@Inject private TimesheetService service;
	

	public Timesheet getTimesheet() {
		return timesheet;
	}
	
	public void setTimesheet(Timesheet timesheet) {
		this.timesheet = timesheet;
	}
	
	public List<Activity> getActivities() {
		return activities;
	}
	
	public List<Project> getProjects() {
		return projects;
	}
		
	public String save() {
        service.updateTimesheet(timesheet);
        return "/ts/list.xhtml?faces-redirect=true";
    }
	public String add() {
		service.addTimesheet(timesheet);
		return "/ts/list.xhtml?faces-redirect=true";
	}
	
	@PostConstruct
	public void init() {
		if (timesheet == null) {
			timesheet = new Timesheet();
		}
		
		projects = service.getAllProjects();
		activities = service.getAllActivities();
	}
	
}
