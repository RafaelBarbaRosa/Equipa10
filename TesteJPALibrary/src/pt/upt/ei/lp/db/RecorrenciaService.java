package pt.upt.ei.lp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class RecorrenciaService {
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

    private boolean saveData(Recorrencia recorrencia) {

           try {
               // Begin a new local transaction so that we can persist new entities
               em.getTransaction().begin();
               em.persist(recorrencia);
               // Commit the transaction, which will cause the entity to
               // be stored in the database
               em.getTransaction().commit();
           } catch (Exception ex) {
               return false;
           }

           return true;
       }

   public RecorrenciaService() {
       this.em = getEM();
   }

   public RecorrenciaService(EntityManager em) {
       this.em = em;
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