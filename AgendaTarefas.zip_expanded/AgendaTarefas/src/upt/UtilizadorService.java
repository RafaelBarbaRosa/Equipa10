package upt;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class UtilizadorService {
	protected EntityManager em;
	
	public UtilizadorService(EntityManager em) {
		this.em = em;
	}
	
	public UtilizadorService() {
	}
	
	public Utilizador updateUtilizador(String nome, String email, String password, List<Tarefa> tarefas) {
		Utilizador u = em.find(Utilizador.class, nome);
		if(u == null) {
			u = new Utilizador();
			em.persist(u);
		}
		u.setNome(nome);
		u.setEmail(email);
		u.setPassword(password);
		u.getTarefas().clear();
		u.getTarefas().addAll(tarefas);
		return u;
	}
	
	public Utilizador updateUtilizador(String nome, String email, String password) {
		Utilizador u = em.find(Utilizador.class, nome);
		if(u == null) {
			u = new Utilizador();
			em.persist(u);
		}
		u.setNome(nome);
		u.setEmail(email);
		u.setPassword(password);
		u.getTarefas().clear();
		return u;
	}
	
	public boolean removeUtilizador(String nome) {
		Utilizador u = findUtilizador(nome);
		if(u != null) {
			em.remove(u);
			return true;
		}
		return false;
	}
	
	public Utilizador findUtilizador(String nome) {
		return em.find(Utilizador.class, nome);
	}
	
	@SuppressWarnings("unchecked")
		public List<Utilizador> findAllUtilizador() {
			Query qd = em.createQuery("Select u from Utilizador u");
			return qd.getResultList();
		}
}
