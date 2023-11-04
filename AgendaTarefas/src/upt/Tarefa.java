package upt;

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
public class Tarefa {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String titulo;
	private String descricao;
	private String dataConclusao;
	private String prioridade;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Tarefa> tarefas = new ArrayList<Tarefa>();
	
	public Tarefa() {
	}
	
	

	public String getPrioridade() {
		return prioridade;
	}



	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(String dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}



	@Override
	public String toString() {
		return "Tarefa: " + titulo+ 
		           "\n Descricao->" + descricao + 
		           "\n DataConclusao->" + dataConclusao + 
		           "\n Prioridade->" + prioridade + 
		           "\n";
	}

	
	
	
}
