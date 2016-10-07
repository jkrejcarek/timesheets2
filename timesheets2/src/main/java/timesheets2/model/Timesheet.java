package timesheets2.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Named(value="timesheet")
@SessionScoped
@Entity(name="timesheets")
public class Timesheet implements Serializable {
	
	@Transient
	@Inject private TimesheetService service;
	
	@Id
	int id;
	private String subject;
	private double hours;
	
	public Timesheet() {
		id = 1;
		hours = 1;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public double getHours() {
		return hours;
	}
	public void setHours(double hours) {
		this.hours = hours;
	}
	
	public String add() {
		System.out.println("Adding book " + getSubject());
		service.addTimesheet(this);
		return "list.xhtml";
	}
}
