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
public class Utilizador {
	public Utilizador(String nome, String email, String password, List<Tarefa> tarefas) {
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.tarefas = tarefas;
	}
	
	public Utilizador() {
		// TODO Auto-generated constructor stub
	}


	public Utilizador(String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}


	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private String nome;
	private String email;
	private String password;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Tarefa> tarefas = new ArrayList<Tarefa>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}
	
	@Override
	public String toString() {
		return "Utilizador: " + nome + 
		           "\n Descricao->" + email + 
		           "\n DataConclusao->" + password + 
		           "\n Tarefas->" + tarefas.toString() +
		           "\n";
	}
}
