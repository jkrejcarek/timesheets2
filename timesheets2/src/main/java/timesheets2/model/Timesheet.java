package timesheets2.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Named(value="timesheet")
@RequestScoped
@Entity(name="timesheet")
public class Timesheet implements Serializable {
	
	@Transient
	@Inject private TimesheetService service;
	
	@TableGenerator(name="Timesheets_Gen",
			table="identities",
		    pkColumnName="gen_name",
		    valueColumnName="gen_val",
		    pkColumnValue="Timesheets_Gen",
		    initialValue=1,
		    allocationSize=100)
	@Id @GeneratedValue(generator="Timesheets_Gen")
	int id;
	private String title;
	private double hours;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="id_activity")
	private Activity activity = new Activity();
	
	//@Column(name="id_activity")
	@Transient private int activityID;
	
	//@Column(name="id_project")
	@Transient private int projectID;
	
	@ManyToOne
	@JoinColumn(name="id_project")
	private Project project = new Project();
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	public Timesheet() {
		date = Calendar.getInstance().getTime();
	}
	
	public List<Activity> getActivities() {
		return service.getAllActivities();
	}
	
	public List<Project> getProjects() {
		return service.getAllProjects();
	}
	
	public int getActivityID() {
		return activityID;
	}

	public void setActivityID(int activityID) {
		this.activityID = activityID;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		this.hours = hours;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String add() {
		service.addTimesheet(this);
		return "list.xhtml";
	}
}
