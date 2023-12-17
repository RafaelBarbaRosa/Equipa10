package pt.upt.ei.lp.db;

import java.util.List;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {
	public static void clearDatabase() {
	    EntityManager em = getEM();
	    em.getTransaction().begin();
	    em.getTransaction().commit();
	    em.close();
	}
	private static final String PERSISTANCE_UNIT_NAME = "LibraryJPA";
	private static EntityManagerFactory factory;
	private static EntityManager emanager = null;
	
	public static void fill() {
		System.out.println("========");
		System.out.println("  FILL");
		System.out.println("========");
		EntityManager em = getEM();
		Query q = null;

		List<Tarefa> tarefas = null;
		List<Categoria> categorias = null;
		List<Estado> estados = null;
		List<Utilizador> utilizadores = null;
		List<Recorrencia> recorrencias = null;
		
		em.getTransaction().begin();
		TarefaService ts = new TarefaService(getEM());
		List<Tarefa> tarefaList = ts.findAllTarefa();
		for (Tarefa t : tarefaList) {
			ts.removeTarefa(t.getTitulo());}
		
		CategoriaService cs = new CategoriaService(getEM());
		List<Categoria> categoriaList = cs.findAllCategoria();
		for(Categoria c : categoriaList) {
			cs.removeCategoria(c.getCategoria());
		}
		
		EstadoService es = new EstadoService(getEM());
		List<Estado> estadoList = es.findAllEstado();
		for(Estado e : estadoList) {
			es.removeEstado(e.getEstado());
		}
		
		UtilizadorService us = new UtilizadorService(getEM());
		List<Utilizador> utilizadorList = us.findAllUtilizador();
		for(Utilizador u : utilizadorList) {
			us.removeUtilizador(u.getNome());
		}
		
		RecorrenciaService rs = new RecorrenciaService(getEM());
		List<Recorrencia> recorrenciaList = rs.findAllRecorrencia();
		for(Recorrencia r : recorrenciaList) {
			rs.removeRecorrencia(r.getRecorrencia());
		}
		
		em.getTransaction().commit();
		
		System.out.println("Cleaned DB");
		System.out.println("--------------------------------------------------------");
		
		em.getTransaction().begin();

		//Adicionar tarefas
		Tarefa t1 = ts.updateTarefa("Limpar o quarto", "Arrumar a cama, limpar a secretária", "19/04/2024", "Alta");	
		Tarefa t2 = ts.updateTarefa("Ir ao ginásio", "Fazer biceps", "28/04/2024", "Baixa");
		Tarefa t3 = ts.updateTarefa("Faculdade","Estudar Laboratório de programação","29/04/2024","Alta");
		Tarefa t4 = ts.updateTarefa("Renovar CC","Sair às 13:30 (abre as 14h)", "22/04/2024","Média");
		Tarefa t5 = ts.updateTarefa("Compras do mês","Lista: batatas, cebolas, tomates, cenouras, água, bifes", "01/04/2024","Média");
		
		//Adicionar categorias
		Categoria c1 = cs.updateCategoria("Pessoal");
		Categoria c2 = cs.updateCategoria("Estudos");
		Categoria c3 = cs.updateCategoria("Trabalho");
		
		//Adicionar estados
		Estado e1 = es.updateEstado("Concluído");
		Estado e2 = es.updateEstado("Pendente");
		
		//Adicionar utilizadores
		Utilizador u1 = us.updateUtilizador("João", "joaocampos2005@gmail.com", "jc2005");
		Utilizador u2 = us.updateUtilizador("Rodrigo", "rodrigofreitas@gmail.com", "carroamarelo1");
		Utilizador u3 = us.updateUtilizador("Catarina", "crocha2001@gmail.com", "mesacastanha2");
		
		//Adicionar recorrências
		Recorrencia r1 = rs.updateRecorrencia("Diária");
		Recorrencia r2 = rs.updateRecorrencia("Semanal");
		Recorrencia r3 = rs.updateRecorrencia("Mensal");
		Recorrencia r4 = rs.updateRecorrencia("Anual");
		
		//Associar as categorias à respetiva tarefa
		c1.getTarefas().add(t1);
		c1.getTarefas().add(t2);
		c2.getTarefas().add(t3);
		c1.getTarefas().add(t4);
		c1.getTarefas().add(t5);
		
		//Associar estados às tarefas
		e1.getTarefas().add(t1);
		e1.getTarefas().add(t2);
		e2.getTarefas().add(t3);
		e2.getTarefas().add(t4);
		e1.getTarefas().add(t5);
		
		//Associar utilizadores às tarefas
		u1.getTarefas().add(t1);
		u1.getTarefas().add(t2);
		u1.getTarefas().add(t3);
		u2.getTarefas().add(t4);
		u3.getTarefas().add(t5);
		
		//Associar recorrências às tarefas
		r2.getTarefas().add(t1);
		r1.getTarefas().add(t2);
		r4.getTarefas().add(t3);
		r4.getTarefas().add(t4);
		r3.getTarefas().add(t5);
		
		em.getTransaction().commit();
		
		tarefas = ts.findAllTarefa();
		categorias = cs.findAllCategoria();
		estados = es.findAllEstado();
		utilizadores = us.findAllUtilizador();
		recorrencias = rs.findAllRecorrencia();

		System.out.println("LISTA DE TAREFAS");
		for (Tarefa ta : tarefas) {
			System.out.println(ta);}
		System.out.println("--------------------------------------------------------\n");
		System.out.println("LISTA DE TAREFAS ORDENADAS POR PRIORIDADE \n");
		System.out.println("TAREFAS COM PRIORIDADE ALTA");
		for (Tarefa ta : tarefas) {
			if(ta.getPrioridade() == "Alta") {
				System.out.println(ta.getTitulo());}
			}

		System.out.println("TAREFAS COM PRIORIDADE MÉDIA");
		for (Tarefa ta : tarefas) {
			if(ta.getPrioridade() == "Média") {
				System.out.println(ta.getTitulo());}
			}
		System.out.println("TAREFAS COM PRIORIDADE BAIXA");
		for (Tarefa ta : tarefas) {
			if(ta.getPrioridade() == "Baixa") {
				System.out.println(ta.getTitulo());}
			}
		
		System.out.println("--------------------------------------------------------");
	    System.out.println("Lista de categorias e respetivas tarefas");
	    for (Categoria c : categorias) {
	        System.out.println("Categoria " + c.getCategoria());
	        for (Tarefa t : c.getTarefas()) {
	            System.out.println(t.getTitulo());
	        }
	        System.out.println();
	    }
	    
	    System.out.println("Estados das tarefas");
		System.out.println("--------------------------------------------------------");
	    for (Estado e : estados) {
	    	System.out.println("Estado " + e.getEstado());
	    	for(Tarefa t : e.getTarefas()) {
	    		System.out.println(t.getTitulo());
	    	}
	    	System.out.println();	
	    }
	    
	    //Imprimir tarefas de acordo com o estado em que se encontram
	    System.out.println("--------------------------------------------------------");
	    System.out.println("Lista de tarefas com estado 'Pendente'");
	    for (Estado e : estados) {
	        if(e.getEstado().equals("Pendente")) {
	            for (Tarefa t : e.getTarefas()) {
	                System.out.println(t.getTitulo());
	            }
	        }
	    }
	    
	    System.out.println("--------------------------------------------------------");
	    System.out.println("Lista de tarefas com estado 'Concluído'");
	    for (Estado e : estados) {
	        if(e.getEstado().equals("Concluído")) {
	            for (Tarefa t : e.getTarefas()) {
	                System.out.println(t.getTitulo());
	            }
	        }
	    }
	    
	    //Imprimir tarefas de cada utilizador
	    System.out.println("Tarefas de cada utilizador");
	    System.out.println("--------------------------------------------------------");
	    for(Utilizador u : utilizadores) {
	    	System.out.println("Utilizador " + u.getNome());
	    	for (Tarefa t : u.getTarefas()) {
	            System.out.println(t.getTitulo());
	        }
	        System.out.println();
	    }
	    
	    //Imprimir tarefas tendo em conta as recorrências
	    System.out.println("Tarefas ordenadas por recorrência");
	    System.out.println("--------------------------------------------------------");
	    for(Recorrencia r : recorrencias) {
	    	System.out.println("Recorrência " + r.getRecorrencia());
	    	for (Tarefa t : r.getTarefas()) {
	            System.out.println(t.getTitulo());
	        }
	        System.out.println();
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
