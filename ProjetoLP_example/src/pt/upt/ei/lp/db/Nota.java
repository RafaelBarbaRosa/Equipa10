package pt.upt.ei.lp.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nota {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private double value;
	private UC uc;
	
	public Nota() {
		id = 0;
		value = 0;
		uc = null;
	}

	public Nota(int id, double value, UC uc) {
		this.id = id;
		this.value = value;
		this.uc = uc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public UC getUc() {
		return uc;
	}

	public void setUc(UC uc) {
		this.uc = uc;
	}

	@Override
	public String toString() {
		return "Nota [value=" + value + ", uc=" + uc + "]";
	}

}
