package upt;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
	
	
	
	public static void clearDatabase() {
	    EntityManager em = getEM();
	    em.getTransaction().begin();
	    // Remova todos os registros e tabelas do banco de dados aqui
	    em.getTransaction().commit();
	    em.close();
	}
	private static final String PERSISTANCE_UNIT_NAME = "LibraryAgendaTarefas";
	private static EntityManagerFactory factory;
	private static EntityManager emanager = null;
	
	public static void fill() {
		System.out.println("========");
		System.out.println("  FILL");
		System.out.println("========");
		EntityManager em = getEM();
		Query q = null;

		List<Tarefa> tarefas = null;
		
		em.getTransaction().begin();
		TarefaService ts = new TarefaService(getEM());
		List<Tarefa> tarefaList = ts.findAllTarefa();
		for (Tarefa t : tarefaList) {
			ts.removeTarefa(t.getTitulo());}
		
		
		
		em.getTransaction().commit();
		
		System.out.println("Cleaned DB");
		System.out.println("--------------------------------------------------------");
		
		em.getTransaction().begin();

		
		Tarefa t1 = ts.updateTarefa("Limpar o quarto", "Arrumar a cama, limpar a secretária", "19/04/2024", "Alta");	
		Tarefa t2 = ts.updateTarefa("Ir ao ginásio", "Fazer biceps", "28/04/2024", "Baixa");
		Tarefa t3 = ts.updateTarefa("Estudo","Estudar Laboratório de programação","29/04/2024","Alta");
		Tarefa t4 = ts.updateTarefa("Renovar CC","Sair às 13:30 (abre as 14h)", "22/04/2024","Média");
		Tarefa t5 = ts.updateTarefa("Compras da semana","Lista: batatas, cebolas, tomates, cenouras, água, bifes", "01/04/2024","Média");
		
		
		
		
		em.getTransaction().commit();
		
		tarefas = ts.findAllTarefa();

		System.out.println("LISTA DE TAREFAS");
		for (Tarefa ta : tarefas) {
			System.out.println(ta);}
		System.out.println("--------------------------------------------------------\n");
		System.out.println("LISTA DE TAREFAS ORDENADAS POR PRIORIDADE \n");
		System.out.println("TAREFAS COM PRIORIDADE ALTA");
		for (Tarefa ta : tarefas) {
			if(ta.getPrioridade() == "Alta") {
				System.out.println(ta);}
			}

		System.out.println("TAREFAS COM PRIORIDADE MÉDIA");
		for (Tarefa ta : tarefas) {
			if(ta.getPrioridade() == "Média") {
				System.out.println(ta);}
			}
		System.out.println("TAREFAS COM PRIORIDADE BAIXA");
		for (Tarefa ta : tarefas) {
			if(ta.getPrioridade() == "Baixa") {
				System.out.println(ta);}
			}
			
			
		System.out.println("--------------------------------------------------------");
		System.out.println("\n\nFinished!!!");}

	
	public static EntityManager getEM() {
		if(emanager == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME);
			emanager = factory.createEntityManager();
		}
		return emanager;
	}
	
	public static void main(String[] args) {
		fill();
	}
  }
