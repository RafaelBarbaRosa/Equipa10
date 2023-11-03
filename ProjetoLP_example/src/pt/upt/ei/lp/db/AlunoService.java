package pt.upt.ei.lp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AlunoService {
	protected EntityManager em;
	
	public AlunoService(EntityManager em) {
		this.em = em;
	}
	
	public Aluno updateAluno(int id, String name, int num, List<Nota> notas) {	
		Aluno a = em.find(Aluno.class, id);
		if (a == null) {
			a = new Aluno();
			em.persist(a);
		}
		a.setId(id);
		a.setName(name);
		a.setNum(num);
		a.getNotas().clear();
		a.getNotas().addAll(notas);
		return a;
	}
	
	public Aluno updateAluno(int id, String name, int num) {	
		Aluno a = em.find(Aluno.class, id);
		if (a == null) {
			a = new Aluno();
			em.persist(a);
		}
		a.setId(id);
		a.setName(name);
		a.setNum(num);
		a.getNotas().clear();
		return a;
	}
	
	public void removeAluno(int id) {
		Aluno l = findAluno(id);
		if(l != null) 
			em.remove(l);
		return;
	}
	
	public Aluno findAluno(int id) {
		return em.find(Aluno.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Aluno> findAllAlunos() {
		Query qd = em.createQuery("Select a from Aluno a");
		return qd.getResultList();
	}

}
