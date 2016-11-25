package timesheets2.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

@Entity(name="projects")
public class Project implements Serializable {
	
	@TableGenerator(name="Project_Gen",
			table="identities",
		    pkColumnName="gen_name",
		    valueColumnName="gen_val",
		    pkColumnValue="Project_Gen",
		    initialValue=1,
		    allocationSize=3)
	@Id @GeneratedValue(generator="Project_Gen")
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
