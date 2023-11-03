package pt.upt.ei.lp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UCService {
	protected EntityManager em;
	
	public UCService(EntityManager em) {
		this.em = em;
	}
	
	public UC updateUC(int id, String name, List<Professor> professores) {	
		UC u = em.find(UC.class, id);
		if (u == null) {
			u = new UC();
			em.persist(u);
		}
		u.setId(id);
		u.setName(name);
		u.getProfessores().clear();
		u.getProfessores().addAll(professores);
		return u;
	}
	
	public UC updateUC(int id, String name) {	
		UC u = em.find(UC.class, id);
		if (u == null) {
			u = new UC();
			em.persist(u);
		}
		u.setId(id);
		u.setName(name);
		u.getProfessores().clear();
		return u;
	}
	
	public void removeUC(int id) {
		UC c = findUC(id);
		if(c != null) 
			em.remove(c);
		return;
	}
	
	public UC findUC(int id) {
		return em.find(UC.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<UC> findAllUC() {
		Query qd = em.createQuery("Select u from UC u");
		return qd.getResultList();
	}
}
