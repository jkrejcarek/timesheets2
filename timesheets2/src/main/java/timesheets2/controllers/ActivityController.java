package timesheets2.controllers;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import timesheets2.beans.TimesheetService;
import timesheets2.model.Activity;
import timesheets2.model.Project;

@Named(value="acontroller")
@ViewScoped
public class ActivityController implements Serializable{
	private String id;
	private Activity activity;
	
	@Inject TimesheetService service;
	
	public void onLoad() {
		if (id != null && id.length() > 0) {
			System.out.println("Loading activity by id " + id);
			activity = service.getActivity(Integer.parseInt(getId()));
		}
	}
	
	public String save() {
		System.out.println("saving activity " + activity);
		if (id != null) {
			service.update(activity);
		} else {
			service.add(activity);
		}
		return "list.xhtml?faces-redirect=true";
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	@PostConstruct
	public void create() {
		System.out.println("Created controller");
		if (activity == null) {
			activity = new Activity();
		}
	}
	
}
