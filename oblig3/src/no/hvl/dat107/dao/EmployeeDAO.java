package no.hvl.dat107.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Department;
import no.hvl.dat107.entity.Employee;

public class EmployeeDAO {

	private EntityManagerFactory emf;

	public EmployeeDAO() {
		emf = Persistence.createEntityManagerFactory("datatyperPersistenceUnit");
	}

	public void addEmployee(Employee employee) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(employee);
			tx.commit();
			System.out.println("New employee is successfully added to the database");

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}


	public Employee retrieveEmployee(int id) {
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Employee.class, id);
		} finally {
			em.close();
		}

	}

	public Employee retrieveEmployee(String username) {
		
		EntityManager em = emf.createEntityManager();
		Employee employee = null;
		String queryString = "SELECT e FROM Employee e " + "WHERE e.username LIKE :username";
		
		try {
			TypedQuery<Employee> query = em.createQuery(queryString, Employee.class);
			query.setParameter("username", username);
			
			employee = query.getSingleResult();
		} catch(NoResultException e){
			System.out.println("No result");
		} finally {
			em.close();
		}
		
		return employee;
	}


	public void updateEmployeePos(Employee id, String position) {
		EntityManager em = emf.createEntityManager();

		try {
			em.getTransaction().begin();
			Employee a = em.merge(id);
			a.setPos(position);
			em.getTransaction().commit();
			System.out.println("The employee's position was updated");

		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();

		} finally {
			em.close();

		}

	}

	public List<Employee> retrieveAllEmployee() {
		EntityManager em = emf.createEntityManager();
		List<Employee> employeeList = null;

		try {
			String queryString = "SELECT e FROM Employee e";
			TypedQuery<Employee> query = em.createQuery(queryString, Employee.class);
			employeeList = query.getResultList();

		} finally {
			em.close();
		}

		return employeeList;

	}

	public void updateEmployeeDepartment(Employee id, Department department) {
		EntityManager em = emf.createEntityManager();

		
		if (id.getId() == department.getDirector().getId()) {
			System.out.println("The employee is director in " + department.getDepartment());
		return;
		}
		
		try {
			em.getTransaction().begin();
			Employee a = em.merge(id);
			a.setDepartment(department.getDepartment());
			em.getTransaction().commit();
			System.out.println("Employee's department was updated");

		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();

		} finally {
			em.close();
		}
	}
	
	public void deleteEmployee(Employee id) {
		EntityManager em = emf.createEntityManager();
		
		DepartmentDAO department = new DepartmentDAO();
		
		
		
		//found the director of the employees department. 
		Employee d = department.retrieveDepartment(id.getDepartment()).getDirector();
		Department de = department.retrieveDepartment(id.getDepartment());
		
		if(id.getId() == d.getId()) {
			System.out.println("The employee you are trying to delete is director in" + de.getDepartment());
			return;
		}
		
//		if()
		//not possible to delete employee if the employee has registered hours in a project 
		
		try {
			em.getTransaction().begin();
			em.remove(em.merge(id));
			em.getTransaction().commit();
			System.out.println("Employee was deleted");
		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			
		} finally {
			em.close();
		}
			
			
		
		
	}
}
