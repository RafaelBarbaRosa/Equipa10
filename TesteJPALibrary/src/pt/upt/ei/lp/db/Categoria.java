package pt.upt.ei.lp.db;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
	public Categoria(String categoria, List<Tarefa> tarefas) {
		super();
		this.categoria = categoria;
		this.tarefas = tarefas;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String categoria;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Tarefa> tarefas = new ArrayList<Tarefa>();
	
	public Categoria() {
	}
	
	public Categoria(String string) {
		// TODO Auto-generated constructor stub
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	@Override
	public String toString() {
		return "Categoria: " + categoria + 
		           "\n Tarefas->" + tarefas.toString() + 
		           "\n";
	}
}
