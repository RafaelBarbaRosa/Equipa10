package upt;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CategoriaService {
	protected EntityManager em;
	
	public CategoriaService(EntityManager em) {
		this.em = em;
	}
	
	public CategoriaService() {
	}
	
	public Categoria updateCategoria(String categoria, List<Tarefa> tarefas) {
		Categoria c = em.find(Categoria.class, categoria);
		if(c == null) {
			c = new Categoria();
			em.persist(c);
		}
		c.setCategoria(categoria);
		c.getTarefas().clear();
		c.getTarefas().addAll(tarefas);
		return c;
	}
	
	public Categoria updateCategoria(String categoria) {
		Categoria c = em.find(Categoria.class, categoria);
		if(c == null) {
			c = new Categoria();
			em.persist(c);
		}
		c.setCategoria(categoria);
		c.getTarefas().clear();
		return c;
	}
	
	public boolean removeCategoria(String categoria) {
		Categoria c = findCategoria(categoria);
		if(c != null) {
			em.remove(c);
			return true;
		}
		return false;
	}
	
	public Categoria findCategoria(String categoria) {
		return em.find(Categoria.class, categoria);
	}

	
	@SuppressWarnings("unchecked")
		public List<Categoria> findAllCategoria(){
			Query qd = em.createQuery("Select c from Categoria c");
			return qd.getResultList();
		}
}
