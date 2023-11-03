package pt.upt.ei.lp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ProfessorService {
protected EntityManager em;
	
	public ProfessorService(EntityManager em) {
		this.em = em;
	}
	
	public Professor updateProfessor(int id, String name, String email, List<UC> ucs) {	
		Professor p = em.find(Professor.class, id);
		if (p == null) {
			p = new Professor();
			em.persist(p);
		}
		p.setId(id);
		p.setName(name);
		p.setEmail(email);
		p.getUCs().clear();
		p.getUCs().addAll(ucs);
		return p;
	}
	
	public Professor updateProfessor(int id, String name, String email) {	
		Professor p = em.find(Professor.class, id);
		if (p == null) {
			p = new Professor();
			em.persist(p);
		}
		p.setId(id);
		p.setName(name);
		p.setEmail(email);
		p.getUCs().clear();
		return p;
	}
	
	public void removeProfessor(int id) {
		Professor r = findProfessor(id);
		if(r != null) 
			em.remove(r);
		return;
	}
	
	public Professor findProfessor(int id) {
		return em.find(Professor.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Professor> findAllProfessores() {
		Query qd = em.createQuery("Select p from Professor p");
		return qd.getResultList();
	}
	
}
