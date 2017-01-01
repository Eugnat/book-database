package main;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dao.AuthorDAO;
import dao.PublishingHouseDAO;
import entities.Author;
import entities.Book;
import entities.Category;
import entities.PublishingHouse;
import utilityclasses.Factory;

public class Main {

	public static void main(String[] args) {
		
		Book book;
		Author author;
		PublishingHouse house;
		AuthorDAO authorDAO = new Author();
		PublishingHouseDAO houseDAO = new PublishingHouse();
		
		EntityManager em = Factory.getEntityManager();
		
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		book = new Book("Java: A Beginner's Guide, Sixth Edition", "978-0071809252", 2014, Category.COMPUTERS);
		house = new PublishingHouse("McGraw-Hill Education", "USA");
		author = new Author("Herbert Schildt", 65);
		
		house.addBook(book, author);
		
		em.persist(house);
		
		tx.commit();
		
		tx.begin();
		
		author = authorDAO.findAuthorByName("Herbert Schildt");
		house = houseDAO.findHouseByName("McGraw-Hill Education");

		book = new Book("Java: The Complete Reference, Ninth Edition", "978-0071808552", 2014, Category.COMPUTERS);
		author.addBook(book);
		house.addBook(book);
		
		tx.commit();
		
		tx.begin();
		
		book = new Book("Head First Java, 2nd Edition", "978-0596009205", 2005, Category.COMPUTERS);
		house = new PublishingHouse("O'Reilly Media", "USA");
		author = new Author("Kathy Sierra", 59);
		
		house.addBook(book, author);
		em.persist(house);
		
		tx.commit();
		
		tx.begin();
		
		book = new Book("Java Persistence with Hibernate 2nd Edition", "978-1617290459", 2015, Category.COMPUTERS);
		house = new PublishingHouse("Manning Publications", "USA");
		author = new Author("Gary Gregory", 45);
		
		house.addBook(book, author);
		em.persist(house);
		
		tx.commit();
		
		tx.begin();
		
		book = new Book("Spring in Action: Covers Spring 4 4th Edition", "978-1617291203", 2014, Category.COMPUTERS);
		house = houseDAO.findHouseByName("Manning Publications");
		author = new Author("Craig Walls", 34);
		
		house.addBook(book, author);
		
		tx.commit();
		
		tx.begin();
		
		book = new Book("Far from the Madding Crowd", "978-0141439655", 2003, Category.FICTION);
		house = new PublishingHouse("Penguin Classics", "United Kingdom");
		author = new Author("Thomas Hardy", 68);
		
		house.addBook(book, author);
		em.persist(house);
		
		tx.commit();
		
		tx.begin();
		
		book = new Book("Far from the Madding Crowd", "978-1515191513", 2015, Category.FICTION);
		house = new PublishingHouse("CreateSpace Independent Publishing Platform", "USA");
		author = authorDAO.findAuthorByName("Thomas Hardy");
		
		author.addBook(book);
		house.addBook(book);
		
		em.persist(house);
		
		tx.commit();
		
		
		Factory.shutdown();

	}

}
