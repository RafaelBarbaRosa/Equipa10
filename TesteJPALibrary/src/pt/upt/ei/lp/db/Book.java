package pt.upt.ei.lp.db;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private String author;
	private String title;
	private Boolean available;

	public Book(String author, String title, Boolean available) {
		this.id = 0;
		this.author = author;
		this.title = title;
		this.available = available;
	}
	
	public Book(int id, String author, String title, Boolean available) {
		this.id = id;
		this.author = author;
		this.title = title;
		this.available = available;
	}
	
	public Book() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean out) {
		this.available = out;
	}

	public String toString() {
		String st = "Book id=" + id + "  author=" + author + "  title=" + title+"  available=" + available;
		return st;
	}

}
