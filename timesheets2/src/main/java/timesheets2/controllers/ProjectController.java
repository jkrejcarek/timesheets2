package timesheets2.controllers;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import timesheets2.beans.TimesheetService;
import timesheets2.model.Project;

@Named(value="pcontroller")
@ViewScoped
public class ProjectController implements Serializable{
	private String id;
	private Project project;
	
	@Inject TimesheetService service;
	
	public void onLoad() {
		if (id != null && id.length() > 0) {
			System.out.println("Loading id " + id);
			project = service.getProject(Integer.parseInt(getId()));
		}
	}
	
	public String save() {
		System.out.println("saving project " + project);
		if (id != null) {
			service.update(project);
		} else {
			service.add(project);
		}
		return "/projects/list.xhtml?faces-redirect=true";
	}
	
	/**
	 * Kontroluje, jestli uvedený název již není použit. Funguje
	 * u nově vytvářených projektů.
	 * @param context
	 * @param component 
	 * @param value Název projektu který se testuje
	 */
	public void validateName(FacesContext context, UIComponent component, Object value) {
		String projectName = (String) value;
		
		for (Project tmpProject : service.getAllProjects()) {
			if (tmpProject.getName().equalsIgnoreCase(projectName)) {
				context.addMessage(component.getClientId(context), new FacesMessage("Projekt '" + projectName + "' již existuje, použijte jiný název"));
				UIInput input = (UIInput) component;
				input.setValid(false);
				return;
			}
		}
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	@PostConstruct
	public void create() {
		System.out.println("Created controller");
		if (project == null) {
			project = new Project();
		}
	}
	
}
