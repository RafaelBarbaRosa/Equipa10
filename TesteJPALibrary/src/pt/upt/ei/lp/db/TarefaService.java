package pt.upt.ei.lp.db;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class TarefaService {
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

    private boolean saveData(Tarefa tarefa) {

           try {
               // Begin a new local transaction so that we can persist new entities
               em.getTransaction().begin();
               em.persist(tarefa);
               // Commit the transaction, which will cause the entity to
               // be stored in the database
               em.getTransaction().commit();
           } catch (Exception ex) {
               return false;
           }

           return true;
       }

   public TarefaService() {
       this.em = getEM();
   }

   public TarefaService(EntityManager em) {
       this.em = em;
   } 
   
   public void atribuirUtilizadorTarefa(Utilizador u, Tarefa t) {
	   em.getTransaction().begin(); u.getTarefas().add(t);
	   em.getTransaction().commit();
   }
   
   public Tarefa addTarefa(String titulo, String descricao, String dataConclusao, String prioridade) {
	   EntityManager em = getEM();
	   
	   try {
		   Tarefa t = em.find(Tarefa.class, titulo);
		   if(t == null) {
			   t = new Tarefa();
			   t.setTitulo(titulo);
		   }
		   
		   t.setDataConclusao(dataConclusao);
		   t.setPrioridade(prioridade);
		   
		   saveData(t);
		   return t;
	   }
	   finally{
		   em.close();  
	   }
   }
   
   public Tarefa updateTarefa(Tarefa tarefa) {
	   EntityManager em = getEM();
	   
	   try {
		   Tarefa t = em.find(Tarefa.class, tarefa.getTitulo());
		   
		   if(t == null) {
			   saveData(tarefa);
			   return tarefa;
		   }
		   t.setTitulo(tarefa.getTitulo());
		   t.setDescricao(tarefa.getDescricao());
		   t.setDataConclusao(tarefa.getDataConclusao());
		   t.setPrioridade(tarefa.getPrioridade());
		   return t;
	   }
	   finally {
		   em.close();
	   }
   }
   
   public Tarefa updateTarefa(String titulo, String descricao, String dataConclusao, String prioridade) {
	   Tarefa f = em.find(Tarefa.class, titulo);
	   
	   if(f == null) {
		   f = new Tarefa(titulo, descricao, dataConclusao, prioridade);
		   
	   }
	   
	   f.setTitulo(titulo);
	   f.setDescricao(descricao);
	   f.setDataConclusao(dataConclusao);
	   f.setPrioridade(prioridade);
	   saveData(f);
	   return(f);
   }
	
	/*public Tarefa updateTarefa(String titulo, String descricao, String dataConclusao, String prioridade, List<Tarefa> tarefas) {	
		Tarefa f = em.find(Tarefa.class, titulo);
		if (f == null) {
			f = new Tarefa();
			em.persist(f);
		}
		f.setTitulo(titulo);
		f.setDescricao(descricao);
		f.setDataConclusao(dataConclusao);
		f.setPrioridade(prioridade);
		f.getTarefas().clear();
		f.getTarefas().addAll(tarefas);
		return f;}*/
	
	
	/*public Tarefa updateTarefa(String titulo, String descricao, String dataConclusao, String prioridade) {	
		Tarefa f = em.find(Tarefa.class, titulo);
		if (f == null) {
			f = new Tarefa();
			em.persist(f);
		}
		f.setTitulo(titulo);
		f.setDescricao(descricao);
		f.setDataConclusao(dataConclusao);
		f.setPrioridade(prioridade);
		f.getTarefas().clear();
		return f;}*/

		
		
		public boolean removeTarefa(String titulo) {
			Tarefa t = findTarefa(titulo);
			if(t != null) { 
				em.remove(t);
				return true;
			}
			
			return false;
		}
		
		public Tarefa findTarefa(String titulo) {
			return em.find(Tarefa.class, titulo);
		}
		
		@SuppressWarnings("unchecked")
		public List<Tarefa> findAllTarefa() {
			Query qd = em.createQuery("Select t from Tarefa t");
			return qd.getResultList();
		}
}