package pt.upt.ei.lp.db;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private String name;
	private int num;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Nota> notas = new ArrayList<Nota>();
	
	public Aluno() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public List<Nota> getNotas() {
		return notas;
	}

	@Override
	public String toString() {
		String x = "Aluno [name=" + name + ", num=" + num + "]";
		for (Nota t : notas) {
			x += "  " + t + "\n";
		}
		return x;
	}
}
