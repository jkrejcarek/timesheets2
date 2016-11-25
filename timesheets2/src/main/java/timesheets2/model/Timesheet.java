package timesheets2.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name="timesheet")
public class Timesheet implements Serializable {
	
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
	
	@ManyToOne
	@JoinColumn(name="id_project")
	private Project project = new Project();
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	public Timesheet() {
		date = Calendar.getInstance().getTime();
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
	

	@Override
	public String toString() {
		return "Timesheet [id=" + id + ", title=" + title + ", hours=" + hours + ", date=" + date + "]";
	}
}
