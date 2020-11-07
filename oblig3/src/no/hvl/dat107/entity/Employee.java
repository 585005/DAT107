package no.hvl.dat107.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3", name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String firstname;
	private String lastname;
	private LocalDate employment;
	private String pos;
	private BigDecimal salary;
	private String department;
    
    @OneToMany(mappedBy="employee")
    private List<Projectparticipation> participation;

	public Employee() {
	}

	public Employee(String username, String firstname, String lastname, LocalDate employment, String pos,
			BigDecimal salary, String department) {
		
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.employment = employment;
		this.pos = pos;
		this.salary = salary;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getEmployment() {
		return employment;
	}

	public void setEmployment(LocalDate employment) {
		this.employment = employment;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	public List<Projectparticipation> getParticipation(){
		return participation;
	}
	

	@Override
	public String toString() {
		return "Ansatt [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", employment=" + employment + ", pos=" + pos + ", salary=" + salary + ", department=" + department
				+ "]";
	}

}
