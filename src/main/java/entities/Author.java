package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

import dao.AuthorDAO;
import utilityclasses.Factory;

@Entity(name="Author")
public class Author implements AuthorDAO {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@NaturalId
	@Column(name="NAME")
	private String name;
	
	@Column(name="AGE")
	private int age;
	
	@OneToMany(mappedBy="author", cascade=CascadeType.ALL, orphanRemoval=true)
	private List<Book> books = new ArrayList<>();
	
	public void addBook(Book book) {
		books.add(book);
		book.setAuthor(this);
	}
	
	public void removeBook(Book book) {
		books.remove(book);
		book.setAuthor(null);
	}
	
	public Author() {}

	public Author(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

	public List<Book> getBooks() {
		return books;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

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
