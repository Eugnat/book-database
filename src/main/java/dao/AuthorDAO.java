package dao;

import java.util.List;

import entities.Author;

public interface AuthorDAO {
	
	Author findAuthorByName(String name);
	
	List<Author> findAll();
	
	Author findById(int id);

}
