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
public class Turma {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private char name;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Aluno> alunos = new ArrayList<Aluno>();
	
	public Turma() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char getName() {
		return name;
	}

	public void setName(char nome) {
		this.name = nome;
	}
	
	public List<Aluno> getAlunos(){
		return alunos;
	}

	@Override
	public String toString() {
		String x = "Turma [name=" + name + "]";
		for(Aluno a: alunos) {
			x += "Aluno [name=" + a.getName() + "] \n";
		}
		return x;
	}
}
