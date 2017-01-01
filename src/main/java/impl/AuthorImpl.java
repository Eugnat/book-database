package impl;

import java.util.List;

import javax.persistence.EntityManager;

import dao.AuthorDAO;
import entities.Author;
import utilityclasses.Factory;

public class AuthorImpl implements AuthorDAO {

	@Override
	public Author findAuthorByName(String name) {
		
		EntityManager em = Factory.getEntityManager();
		
		Author author = em.createQuery("select a from Author a where name = :name", Author.class)
				                    .setParameter("name", name)
				                    .getSingleResult();
		
		return author;
	}

	@Override
	public List<Author> findAll() {
		
		EntityManager em = Factory.getEntityManager();
		
		List<Author> authorList = em.createQuery("select a from Author a", Author.class).getResultList();
		
		return authorList;
	}

	@Override
	public Author findById(int id) {
		
		EntityManager em = Factory.getEntityManager();
		
		Author author = em.find(Author.class, id);
		
		return author;
		
	}
}
