package pt.upt.ei.lp.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BookService {

	private static final String PERSISTENCE_UNIT_NAME = "LibraryJPA";
	private static EntityManagerFactory factory;
	private static EntityManager em = null;

	private static EntityManager getEM() {
		if (em == null) {
			factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
			em = factory.createEntityManager();
		}
		return em;
	}
	
	private boolean saveData(Book book) {
		
		try {
			// Begin a new local transaction so that we can persist new entities
			em.getTransaction().begin();
			em.persist(book);
			// Commit the transaction, which will cause the entity to
			// be stored in the database
			em.getTransaction().commit();
		} catch (Exception ex) {
			return false;
		}
		
		return true;
	}

	public BookService(EntityManager em) {
		this.em = em;
	}

	public BookService() {
		this.em = getEM();
	}

	public Book updateBook(Book book) {
		Book b = em.find(Book.class, book.getId());

		if (b == null) {
			saveData(book);

			return book;
		}

		// b.setId(id);
		b.setTitle(book.getTitle());
		b.setAuthor(book.getAuthor());
		b.setAvailable(book.getAvailable());

		saveData(b);

		return b;
	}

	public Book updateBook(int id, String title, String author, Boolean available) {
		Book b = em.find(Book.class, id);
		if (b == null) {
			b = new Book();

			saveData(b);
		}

		b.setId(id);
		b.setTitle(title);
		b.setAuthor(author);
		b.setAvailable(available);

		saveData(b);

		return b;
	}

	public boolean removeBook(int id) {
		Book b = findBook(id);
		if (b != null) {
			// Begin a new local transaction so that we can persist new entities
			em.getTransaction().begin();
			em.remove(b);
			// Commit the transaction, which will cause the entity to
			// be stored in the database
			em.getTransaction().commit();

			return true;
		}

		return false;
	}

	public Book findBook(int id) {
		return em.find(Book.class, id);
	}

	public List<Book> findAllBooks() {
		Query qd = em.createQuery("SELECT b FROM Book b");
		return qd.getResultList();
	}

}