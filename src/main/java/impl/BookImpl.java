package impl;

import java.util.List;

import javax.persistence.EntityManager;

import dao.BookDAO;
import entities.Author;
import entities.Book;
import entities.Category;
import utilityclasses.Factory;

public class BookImpl implements BookDAO {

	@Override
	public List<Book> findByTitle(String title) {
		
		EntityManager em = Factory.getEntityManager();
		
		List<Book> bookList = em.createQuery("select b from Book b where b.title = :title", Book.class)
								.setParameter("title", title)
								.getResultList();
		
		return bookList;
	}

	@Override
	public List<Book> findAll() {
		
		EntityManager em = Factory.getEntityManager();
		List<Book> bookList = em.createQuery("select b from Book b", Book.class).getResultList();
		
		return bookList;
	}

	@Override
	public Book findById(int id) {
		
		EntityManager em = Factory.getEntityManager();
		Book book = em.find(Book.class, id);
		
		return book;
	}

	@Override
	public List<Book> findByCategory(Category type) {
		
		EntityManager em = Factory.getEntityManager();
		
		List<Book> bookList = em.createQuery("select b from Book b where b.type = :type", Book.class)
				                .setParameter("type", type)
				                .getResultList();
		
		return bookList;
	}


	@Override
	public List<Book> findByAuthor(String name) {
		
		EntityManager em = Factory.getEntityManager();
		
		Author author = em.createQuery("select a from Author a where a.name = :name", Author.class)
						  .setParameter("name", name)
						  .getSingleResult();
		
		return author.getBooks();
	}
}
