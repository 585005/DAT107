package no.hvl.dat107.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.entity.Employee;
import no.hvl.dat107.entity.Project;

public class ProjectDAO {
	
	private EntityManagerFactory emf;
	
	public ProjectDAO() {
		emf = Persistence.createEntityManagerFactory("datatyperPersistenceUnit");
	}
	
	public void addProject(Project project) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(project);
			tx.commit();
			System.out.println("New project is successfully added to the database");

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public Project retrieveProject(int id) {
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Project.class, id);
		} finally {
			em.close();
		}

	}
	}


