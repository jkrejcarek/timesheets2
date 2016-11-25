package timesheets2.controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import timesheets2.beans.TimesheetService;
import timesheets2.model.Project;

@Named
@RequestScoped
public class ProjectsViewController {

	@Inject TimesheetService service;
	List<Project> projects;
	
	public List<Project> getProjects() {
		return projects;
	}
	
	@PostConstruct
	public void postConstruct() {
		projects = service.getAllProjects();		
	}
}
