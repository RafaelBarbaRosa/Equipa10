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
public class UC {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private String name;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<Professor> professores = new ArrayList<Professor>();

	public UC() {
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
	
	public List<Professor> getProfessores() {
		return professores;
	}

	@Override
	public String toString() {
		String x =  "UC [name=" + name + "]";
		for(Professor p : professores) {
			x += " Professores [name=" + p.getName() + "] \n";
		}
		return x;
	}

}
