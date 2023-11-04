package upt;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class TarefaService {
	protected EntityManager em;
	
	public TarefaService(EntityManager em) {
		this.em = em;
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

		
		
		public void removeTarefa(String titulo) {
			Tarefa t = findTarefa(titulo);
			if(t != null) 
				em.remove(t);
			return;
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
