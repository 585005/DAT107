package main;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import no.hvl.dat107.dao.DepartmentDAO;
import no.hvl.dat107.dao.EmployeeDAO;
import no.hvl.dat107.dao.ProjectDAO;
import no.hvl.dat107.dao.ProjectparticipationDAO;
import no.hvl.dat107.entity.Employee;
import no.hvl.dat107.entity.Project;
import no.hvl.dat107.entity.Projectparticipation;

public class Meny {
	
	public static void main(String[] args) {
	
	DepartmentDAO department = new DepartmentDAO();
	
	EmployeeDAO employee = new EmployeeDAO();
	
	ProjectDAO project = new ProjectDAO();
	
	ProjectparticipationDAO projectp = new ProjectparticipationDAO();
	
	Scanner sc = new Scanner(System.in);
	
	int input = 1;
	
	while(input != 0) {
		meny();
		input = sc.nextInt();
		
		switch(input) {
		case 1: System.out.println("Oppgi ansattID: ");
				int empID = sc.nextInt();
				System.out.println(employee.retrieveEmployee(empID));
				break;
		case 2: sc.nextLine();
				System.out.println("Oppgi brukernavn");
				String username = sc.nextLine();
				System.out.println(employee.retrieveEmployee(username));
				break;
		case 3: List<Employee> all = employee.retrieveAllEmployee();
				for(Employee e : all) {
					System.out.println(e);
				}
				break;
		case 4: sc.nextLine();
				System.out.println("Skriv inn ansattID: ");
				int empID2 = sc.nextInt();
				Employee e = employee.retrieveEmployee(empID2);
				sc.nextLine();
				System.out.println("Skriv inn ny posisjon: ");
				String newPos = sc.nextLine();
				employee.updateEmployeePos(e, newPos);
				break;
		
		case 5: sc.nextLine();
				EmployeeDAO newEmp = new EmployeeDAO();
				System.out.println("Skriv inn brukernavn");
				String username1 = sc.nextLine();
				System.out.println("Skriv inn fornavn: ");
				String firstname = sc.nextLine();
				System.out.println("Skriv inn etternavn: ");
				String lastname = sc.nextLine();
				System.out.println("Skriv inn stilling");
				String position = sc.nextLine();
				System.out.println("Skriv inn lønn");
				BigDecimal salary = sc.nextBigDecimal();
				System.out.println("Skriv inn avdeling, avdelingen må eksistere");
				sc.nextLine();
				String department1 = sc.nextLine();
				LocalDate date = LocalDate.now();
				Employee newEmployee = new Employee(username1, firstname, lastname, date, position, salary, department1);
				newEmp.addEmployee(newEmployee);
				break;
		case 6: System.out.println("Skriv inn avdelings id: ");
				int departmentID = sc.nextInt();
				System.out.println(department.retrieveDepartment(departmentID).toString());
				break;	
		case 7: sc.nextLine();
				ProjectDAO p = new ProjectDAO();
				System.out.println("Skriv inn prosjekt id: ");
				int pid = sc.nextInt();
				System.out.println(p.retrieveProject(pid));
				break;
		case 8: sc.nextLine();
				System.out.println("Skriv inn prosjektdeltakelses nr: ");
				int pp = sc.nextInt();
				System.out.println(projectp.retrieveParticipation(pp));
				break;
		case 9: sc.nextLine();
				System.out.println("Skriv inn prosjekt navn: ");
				String projectName = sc.nextLine();
				Project pep = new Project(projectName);
				project.addProject(pep);	
				break;
		case 10: System.out.println("Skriv inn ansatt id:");
				int pid2 = sc.nextInt();
				Employee e2 = employee.retrieveEmployee(pid2);
				sc.nextLine();
				System.out.println("Skriv inn prosjekt id:");
				int pid3 = sc.nextInt();
				Project p2 = project.retrieveProject(pid3);
				sc.nextLine();
				System.out.println("Skriv inn timer jobbet: ");
				int pid4 = sc.nextInt();
				sc.nextLine();
				System.out.println("Skriv inn stillingsbeskrivelse");
				String pid5 = sc.nextLine();
				Projectparticipation d = new Projectparticipation(e2, p2, pid4, pid5);
				projectp.addParticipation(d);
				break;
		case 11: System.out.println("Skriv inn prosjektdeltakelse id");
				int pid6 = sc.nextInt();
				Projectparticipation s = projectp.retrieveParticipation(pid6);
				sc.nextLine();
				System.out.println("Skriv inn de nye timene");	
				int pid7 = sc.nextInt();
				projectp.updateHours(s, pid7);
				break;
				
		}
		
		System.out.println("---------------------------------");
	}
	
	sc.close();
	System.out.println("Programmet er ferdig");

	}
	
	public static void meny() {
		System.out.println("Meny:");
		System.out.println("0: Avslutt");
		System.out.println("1: Søk på ansatt med ansattID");
		System.out.println("2: Søk på ansatt med brukernavn");
		System.out.println("3: Se alle ansatte");
		System.out.println("4: Oppdatere stilling på en ansatt");
		System.out.println("5: Legge til en ny ansatt");
		System.out.println("6: Søke etter avdeling med avdelings id");
		System.out.println("7: Skriv ut et prosjekt");
		System.out.println("8: Skriv ut prosjektdeltakelses med id");
		System.out.println("9: Legge til ett nytt prosjekt");
		System.out.println("10: Legge til en ny ansatt i prosjektdeltakelse");
		System.out.println("11: Oppdatere timer i prosjektdeltakelse");
		
	}
	
}
