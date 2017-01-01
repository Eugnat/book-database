package dao;

import java.util.List;

import entities.Book;
import entities.Category;

public interface BookDAO {
	
	List<Book> findByTitle(String name);
	List<Book> findAll();
	Book findById(int id);
	List<Book> findByCategory(Category category);
	List<Book> findByAuthor(String name);
	

}
