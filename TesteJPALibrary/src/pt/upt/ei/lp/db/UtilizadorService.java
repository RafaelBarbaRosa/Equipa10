package pt.upt.ei.lp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class UtilizadorService {
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

    private boolean saveData(Utilizador utilizador) {

           try {
               // Begin a new local transaction so that we can persist new entities
               em.getTransaction().begin();
               em.persist(utilizador);
               // Commit the transaction, which will cause the entity to
               // be stored in the database
               em.getTransaction().commit();
           } catch (Exception ex) {
               return false;
           }

           return true;
       }

   public UtilizadorService() {
       this.em = getEM();
   }

   public UtilizadorService(EntityManager em) {
       this.em = em;
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
