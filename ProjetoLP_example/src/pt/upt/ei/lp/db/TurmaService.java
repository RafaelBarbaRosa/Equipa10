package pt.upt.ei.lp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TurmaService {
protected EntityManager em;
	
	public TurmaService(EntityManager em) {
		this.em = em;
	}
	
	public Turma updateTurma(int id, char name, List<Aluno> alunos) {	
		Turma t = em.find(Turma.class, id);
		if (t == null) {
			t = new Turma();
			em.persist(t);
		}
		t.setId(id);
		t.setName(name);
		t.getAlunos().clear();
		t.getAlunos().addAll(alunos);
		return t;
	}
	
	public Turma updateTurma(int id, char name) {	
		Turma t = em.find(Turma.class, id);
		if (t == null) {
			t = new Turma();
			em.persist(t);
		}
		t.setId(id);
		t.setName(name);
		t.getAlunos().clear();
		return t;
	}
	
	public void removeTurma(int id) {
		Turma t = findTurma(id);
		if(t != null) 
			em.remove(t);
		return;
	}
	
	public Turma findTurma(int id) {
		return em.find(Turma.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Turma> findAllTurmas() {
		Query qd = em.createQuery("Select t from Turma t");
		return qd.getResultList();
	}

}
