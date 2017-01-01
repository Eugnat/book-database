package entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.NaturalId;

import dao.BookDAO;
import utilityclasses.Factory;

@Entity(name="Book")
public class Book implements BookDAO {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private int id;
	
	@Column(name="TITLE")
	private String title;
	
	@NaturalId
	@Column(name="ISBN")
	private String isbn;
	
	@Column(name="ISSUE_YEAR")
	private int issueYear;
	
	@ManyToOne
	@JoinColumn(name="AUTHOR_ID", foreignKey=@ForeignKey(name="AUTHOR_ID_FK"))
	private Author author;
	
	@ManyToOne
	@JoinColumn(name="HOUSE_ID", foreignKey=@ForeignKey(name="HOUSE_ID_FK"))
	private PublishingHouse publishingHouse;
	
	@Enumerated(EnumType.STRING)
	private Category type;
	
	public Book() {}

	public Book(String title, String isbn, int issueYear, Category type) {
		super();
		this.title = title;
		this.isbn = isbn;
		this.issueYear = issueYear;
		this.type = type;
	}

	public Category getType() {
		return type;
	}

	public void setType(Category type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getIssueYear() {
		return issueYear;
	}

	public void setIssueYear(int issueYear) {
		this.issueYear = issueYear;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public int getId() {
		return id;
	}
	
	

	public PublishingHouse getPublishingHouse() {
		return publishingHouse;
	}

	public void setPublishingHouse(PublishingHouse publishingHouse) {
		this.publishingHouse = publishingHouse;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", isbn=" + isbn + ", issueYear=" + issueYear + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + issueYear;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Book other = (Book) obj;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (issueYear != other.issueYear)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

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
