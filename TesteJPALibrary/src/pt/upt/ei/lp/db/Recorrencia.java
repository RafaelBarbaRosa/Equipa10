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
public class Recorrencia {
	public Recorrencia(String recorrencia, List<Tarefa> tarefas) {
		super();
		this.recorrencia = recorrencia;
		this.tarefas = tarefas;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String recorrencia;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Tarefa> tarefas = new ArrayList<Tarefa>();
	
	public Recorrencia() {
	}

	public Recorrencia(String string) {
		// TODO Auto-generated constructor stub
	}

	public String getRecorrencia() {
		return recorrencia;
	}

	public void setRecorrencia(String recorrencia) {
		this.recorrencia = recorrencia;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	@Override
	public String toString() {
		return "Recorrência: " + recorrencia + 
		           "\n Tarefas->" + tarefas.toString() + 
		           "\n";
	}
}
