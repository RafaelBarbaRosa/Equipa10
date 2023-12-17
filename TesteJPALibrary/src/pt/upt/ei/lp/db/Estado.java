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
public class Estado {
	public Estado(String estado, List<Tarefa> tarefas) {
		super();
		this.estado = estado;
		this.tarefas = tarefas;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String estado;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Tarefa> tarefas = new ArrayList<Tarefa>();
	
	public Estado() {
		
	}

	public Estado(String string) {
		// TODO Auto-generated constructor stub
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	@Override
	public String toString() {
		return "Estado: " + estado + 
		           "\n Tarefas->" + tarefas.toString() + 
		           "\n";
	}
}
