package no.hvl.dat107.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(schema = "oblig3", name = "projectparticipation")
public class Projectparticipation {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int projectparticipation_id;
	
	private int hours;
	
	private String position;
	
	@ManyToOne 
	@JoinColumn(name="Employee_id")
	private Employee employee; 
	
	@ManyToOne 
	@JoinColumn(name="Project_id")
	private Project project; 
	
	
	public Projectparticipation() {}
	
	public Projectparticipation(Employee employee, Project project, int hours, String position) {
		
		this.employee = employee;
		this.project = project;
		this.hours = hours; 
		this.position = position;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "Projectparticipation [projectparticipation_id=" + projectparticipation_id + ", hours=" + hours
				+ ", employee=" + employee + ", project=" + project + "]";
	}

	public int getProjectparticipation_id() {
		return projectparticipation_id;
	}

	public void setProjectparticipation_id(int projectparticipation_id) {
		this.projectparticipation_id = projectparticipation_id;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
	

}
