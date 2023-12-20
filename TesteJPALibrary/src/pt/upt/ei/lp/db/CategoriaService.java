package pt.upt.ei.lp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class CategoriaService {
	private static final String PERSISTENCE_UNIT_NAME = "LibraryJPA";
    private static EntityManagerFactory factory;
    private static EntityManager em = null;

    public static EntityManager getEM() {
           if (em == null) {
               factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
               em = factory.createEntityManager();
           }
           return em;
       }

    private boolean saveData(Categoria categoria) {

           try {
               // Begin a new local transaction so that we can persist new entities
               em.getTransaction().begin();
               em.persist(categoria);
               // Commit the transaction, which will cause the entity to
               // be stored in the database
               em.getTransaction().commit();
           } catch (Exception ex) {
               return false;
           }

           return true;
       }

   public CategoriaService() {
       this.em = getEM();
   }

   public CategoriaService(EntityManager em) {
       this.em = em;
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