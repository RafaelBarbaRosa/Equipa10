package upt;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EstadoService {
	protected EntityManager em;
	
	public EstadoService(EntityManager em){
		this.em = em;	
	}
	
	public EstadoService() {
	}
	
	public Estado updateEstado(String estado, List<Tarefa> tarefas) {
		Estado e = em.find(Estado.class, estado);
		if(e == null) {
			e = new Estado();
			em.persist(e);
		}
		e.setEstado(estado);
		e.getTarefas().clear();
		e.getTarefas().addAll(tarefas);
		return e;
	}
	
	public Estado updateEstado(String estado) {
		Estado e = em.find(Estado.class, estado);
		if(e == null) {
			e = new Estado();
			em.persist(e);
		}
		e.setEstado(estado);
		e.getTarefas().clear();
		return e;
	}
	
	public boolean removeEstado(String estado) {
		Estado e = findEstado(estado);
		if(e != null) {
			em.remove(e);
			return true;
		}
		return false;
	}
	
	public Estado findEstado(String estado) {
		return em.find(Estado.class, estado);
	}
	
	@SuppressWarnings("unchecked")
	public List<Estado> findAllEstado(){
		Query qd = em.createQuery("Select e from Estado e");
		return qd.getResultList();
	}
}
