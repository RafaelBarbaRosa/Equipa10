package upt;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class RecorrenciaService {
protected EntityManager em;
	
	public RecorrenciaService(EntityManager em) {
		this.em = em;
	}
	
	public RecorrenciaService() {
	}
	
	public Recorrencia updateRecorrencia(String recorrencia, List <Tarefa> tarefas) {
		Recorrencia r = em.find(Recorrencia.class, recorrencia);
		if(r == null) {
			r = new Recorrencia();
			em.persist(r);
		}
		r.setRecorrencia(recorrencia);
		r.getTarefas().clear();
		r.getTarefas().addAll(tarefas);
		return r;
	}
	
	public Recorrencia updateRecorrencia(String recorrencia) {
		Recorrencia r = em.find(Recorrencia.class, recorrencia);
		if(r == null) {
			r = new Recorrencia();
			em.persist(r);
		}
		r.setRecorrencia(recorrencia);
		r.getTarefas().clear();
		return r;
	}
	
	public boolean removeRecorrencia(String recorrencia) {
		Recorrencia r = findRecorrencia(recorrencia);
		if(r != null) {
			em.remove(r);
			return true;
		}
		return false;
	}
	
	public Recorrencia findRecorrencia(String recorrencia) {
		return em.find(Recorrencia.class, recorrencia);
	}

	@SuppressWarnings("unchecked")
	public List<Recorrencia> findAllRecorrencia(){
		Query qd = em.createQuery("Select r from Recorrencia r");
		return qd.getResultList();
	}
}
