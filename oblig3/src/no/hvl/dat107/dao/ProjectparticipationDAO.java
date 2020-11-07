package no.hvl.dat107.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.entity.Employee;
import no.hvl.dat107.entity.Projectparticipation;

public class ProjectparticipationDAO {
	
	private EntityManagerFactory emf;

	public ProjectparticipationDAO() {
		emf = Persistence.createEntityManagerFactory("datatyperPersistenceUnit");
	}
	
	public void addParticipation(Projectparticipation participation) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(participation);
			tx.commit();
			System.out.println("New projectparticipation is successfully added to the database");

		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}
	}
	
	public Projectparticipation retrieveParticipation(int id) {
		EntityManager em = emf.createEntityManager();

		try {
			return em.find(Projectparticipation.class, id);
		} finally {
			em.close();
		}

	}
	
	public void updateHours(Projectparticipation id, int hours) {
		EntityManager em = emf.createEntityManager();
		
		try {
		em.getTransaction().begin();
		Projectparticipation a = em.merge(id);
		a.setHours(hours);
		em.getTransaction().commit();
		System.out.println("The employye's hours in the project was updated");

	} catch (Throwable e) {
		e.printStackTrace();
		em.getTransaction().rollback();

	} finally {
		em.close();

	}
	}
	
	public void updatePosition(Projectparticipation id, String position) {
		EntityManager em = emf.createEntityManager();
		
		try {
		em.getTransaction().begin();
		Projectparticipation a = em.merge(id);
		a.setPosition(position);
		em.getTransaction().commit();
		System.out.println("The employye's position in the project was updated");

	} catch (Throwable e) {
		e.printStackTrace();
		em.getTransaction().rollback();

	} finally {
		em.close();

	}
	}
	
	

}

