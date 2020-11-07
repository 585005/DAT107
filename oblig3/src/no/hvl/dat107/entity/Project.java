package no.hvl.dat107.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3", name = "project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int project_id;
	private String project_name;
	
	@OneToMany(mappedBy="project")
	private List<Projectparticipation> participation;
	

	public Project() {}
	
	public Project(String project_name) {
		
		this.project_name = project_name;
		
	}

	public int getProject_id() {
		return project_id;
	}

	public void setProject_id(int project_id) {
		this.project_id = project_id;
	}

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	
	public List<Projectparticipation> getParticipation() {
		return participation;
	}


	@Override
	public String toString() {
		return "Project [project_id=" + project_id + ", project_name=" + project_name + "]";
	}

	
	

}
