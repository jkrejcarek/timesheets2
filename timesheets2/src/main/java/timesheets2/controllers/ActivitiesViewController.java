package timesheets2.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import timesheets2.beans.TimesheetService;
import timesheets2.model.Activity;

@Named
@RequestScoped
public class ActivitiesViewController {

	@Inject TimesheetService service;
	List<Activity> activities;
	
	public List<Activity> getActivities() {
		return activities;
	}
	
	@PostConstruct
	public void postConstruct() {
		activities = service.getAllActivities();		
	}
}
