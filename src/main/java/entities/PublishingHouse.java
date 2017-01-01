package entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.NaturalId;
import impl.PublishingHouseImpl;

@Entity(name="PublishingHouse")
public class PublishingHouse extends PublishingHouseImpl {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NaturalId
	@Column(name="HOUSE_NAME")
	private String houseName;
	
	@Column(name="COUNTRY")
	private String country;
	
	@OneToMany(mappedBy="publishingHouse", cascade=CascadeType.ALL, orphanRemoval=true)
	List<Book> books = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
	List<Author> authors = new ArrayList<>();
	
	public void addBook(Book book, Author author) {
		
		author.addBook(book);
		authors.add(author);
		books.add(book);
		book.setPublishingHouse(this);
		
		
	}
	
	public void removeBook(Book book, Author author) {
		
		author.removeBook(book);
		authors.remove(author);
		books.remove(book);
		book.setPublishingHouse(null);
	}
	
	public void addBook(Book book) {
		books.add(book);
		book.setPublishingHouse(this);
	}
	
	public void removeBook(Book book) {
		books.remove(book);
		book.setPublishingHouse(null);
	}
	
	public PublishingHouse() {}

	public PublishingHouse(String houseName, String country) {
		super();
		this.houseName = houseName;
		this.country = country;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public int getId() {
		return id;
	}

	public List<Book> getBooks() {
		return books;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	@Override
	public String toString() {
		return "PublishingHouse [id=" + id + ", houseName=" + houseName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((houseName == null) ? 0 : houseName.hashCode());
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
		PublishingHouse other = (PublishingHouse) obj;
		if (houseName == null) {
			if (other.houseName != null)
				return false;
		} else if (!houseName.equals(other.houseName))
			return false;
		return true;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	

}
