package upt;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TarefaService {
	protected EntityManager em;
	
	public TarefaService(EntityManager em) {
		this.em = em;
	}
	
	public TarefaService() {
	}
	
	public Tarefa updateTarefa(String titulo, String descricao, String dataConclusao, String prioridade, List<Tarefa> tarefas) {	
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
		return f;}
	
	
	public Tarefa updateTarefa(String titulo, String descricao, String dataConclusao, String prioridade) {	
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
		return f;}

		
		
		public boolean removeTarefa(String titulo) {
			Tarefa t = findTarefa(titulo);
			if(t != null) {
				em.remove(t);
			return true;
			}
			return false;
		}
		
		//public void removeTarefa(String titulo) {
		    //Tarefa t = findTarefa(titulo);
		    //if(t != null) {
		        // Remover a tarefa das listas de tarefas em outras entidades
		        //for(Categoria c : t.getCategorias()) {
		            //c.getTarefas().remove(t);
		        //}
		        //for(Estado e : t.getEstados()) {
		            //e.getTarefas().remove(t);
		        //}
		        //for(Utilizador u : t.getUtilizadores()) {
		            //u.getTarefas().remove(t);
		        //}
		        //for(Recorrencia r : t.getRecorrencias()) {
		            //r.getTarefas().remove(t);
		        //}
		        // Agora você pode remover a tarefa
		        //em.remove(t);
		    //}
		    //return;
		//}

		
		public Tarefa findTarefa(String titulo) {
			return em.find(Tarefa.class, titulo);
		}
		
		@SuppressWarnings("unchecked")
		public List<Tarefa> findAllTarefa() {
			Query qd = em.createQuery("Select t from Tarefa t");
			return qd.getResultList();
		}
}
