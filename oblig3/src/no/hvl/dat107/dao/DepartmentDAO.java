package no.hvl.dat107.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Department;
import no.hvl.dat107.entity.Employee;

public class DepartmentDAO {

	private EntityManagerFactory emf;

	public DepartmentDAO() {
		emf = Persistence.createEntityManagerFactory("datatyperPersistenceUnit");
	}

	public Department retrieveDepartment(int id) {
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Department.class, id);

		} finally {
			em.close();
		}

	}
	
	
	public Department retrieveDepartment(String department) {
		EntityManager em = emf.createEntityManager();

		List<Department> departmentList = null;
		int id = 0;

		try {
			String queryString = "SELECT e department FROM Department e WHERE department = :department ";
			TypedQuery<Department> query = em.createQuery(queryString, Department.class);
			departmentList = query.getResultList();
			for (Department e : departmentList) {
				if (e.getDepartment() == department) {
					id = e.getDepartment_id();
				}
			}
			return em.find(Department.class, id);

		} finally {
			em.close();
		}
	}

	public void addDepartment(Department department, Employee employee) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			
			em.persist(department);
			Employee a = em.merge(employee);
			Department d = em.merge(department);
			d.setDirector(a);
			a.setDepartment(d.getDepartment());
			em.getTransaction().commit();

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}

		} finally {
			em.close();
		}
	}

	public void addDepartmentObject(int department_id, String department, Employee director) {

		DepartmentDAO departmentDAO = new DepartmentDAO();
		EmployeeDAO employee = new EmployeeDAO();

		Employee e = employee.retrieveEmployee(director.getId());

		Department d = new Department(department_id, department, director);

		departmentDAO.addDepartment(d, e);
	}

	public void updateDirector(Department d, Employee emp) {
		EntityManager em = emf.createEntityManager();

		EmployeeDAO employeedao = new EmployeeDAO();
		
		try {
			em.getTransaction().begin();
			Department department = em.merge(d);
			Employee employee = em.merge(emp);
			department.setDirector(employee);

			employeedao.updateEmployeeDepartment(employee, department);

			em.getTransaction().commit();
			System.out.println("Departments director was updated");

		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();

		} finally {
			em.close();

		}
	}

	public List<Employee> retrieveAllEmployeeDepartment(String department) {
		EntityManager em = emf.createEntityManager();
		List<Employee> employeeList = null;

		try {
			String queryString = "SELECT e FROM Employee e GROUP BY e.id HAVING e.department = :department ";
			TypedQuery<Employee> query = em.createQuery(queryString, Employee.class).setParameter("department", department);
			employeeList = query.getResultList();

		} finally {
			em.close();
		}
		
		return employeeList;

	}
	
	
	public void deleteDepartment(Department id, String department) {
		EntityManager em = emf.createEntityManager();
		
		EmployeeDAO employee = new EmployeeDAO();
		DepartmentDAO dep = new DepartmentDAO();
		
		List<Employee> employeeList = retrieveAllEmployeeDepartment(id.getDepartment());
		
		if(employeeList.isEmpty()) {
			try {
				em.getTransaction().begin();
				em.remove(em.merge(id));
				em.getTransaction().commit();
				
			} catch (Throwable e) {
				e.printStackTrace();
				em.getTransaction().rollback();
				
			} finally {
				em.close();
			}
			
		} else {
		
		try {
			em.getTransaction().begin();
			
			for(Employee k : employeeList) {
				k.setDepartment(department);
			}
			
			
			
			//do I need to delete the FK from department to employee (director)? 
			
			em.remove(em.merge(id));
			em.getTransaction().commit();
			System.out.println("Department was deleted");
		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
			
		} finally {
			em.close();
		}
		}
}
}
