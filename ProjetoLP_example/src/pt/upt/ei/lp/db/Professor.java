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
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private String name;
	private String email;
	
	@OneToMany(cascade= {CascadeType.ALL},fetch=FetchType.LAZY)
	List<UC> ucs = new ArrayList<UC>();
	
	public Professor() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UC> getUCs() {
		return ucs;
	}

	@Override
	public String toString() {
		String x = "Professor [name=" + name + ", email=" + email + "]";
		for (UC u : ucs) {
			x += " " + u + "\n";
		}
		return x;
	}
	
	
}
