package timesheets2.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import timesheets2.beans.TimesheetService;
import timesheets2.model.Activity;
import timesheets2.model.Project;
import timesheets2.model.Timesheet;

@Named(value="controller")
@RequestScoped
public class TimesheetController {
	private String id;
	
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@PostConstruct
	public void init() {
		if (timesheet == null) {
			timesheet = new Timesheet();
		}
		
		projects = service.getAllProjects();
		activities = service.getAllActivities();
	}
	
	public void onload() {
		if (id != null && id.length() > 0) {
			timesheet = service.getTimesheet(Integer.parseInt(id));
		}
	}
	
}
