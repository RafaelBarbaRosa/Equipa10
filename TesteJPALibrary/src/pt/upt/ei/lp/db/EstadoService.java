package pt.upt.ei.lp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EstadoService {
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

    private boolean saveData(Estado estado) {

           try {
               // Begin a new local transaction so that we can persist new entities
               em.getTransaction().begin();
               em.persist(estado);
               // Commit the transaction, which will cause the entity to
               // be stored in the database
               em.getTransaction().commit();
           } catch (Exception ex) {
               return false;
           }

           return true;
       }

   public EstadoService() {
       this.em = getEM();
   }

   public EstadoService(EntityManager em) {
       this.em = em;
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
