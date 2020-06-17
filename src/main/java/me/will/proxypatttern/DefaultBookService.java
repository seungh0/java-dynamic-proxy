package me.will.proxypatttern;

public class DefaultBookService implements BookService {

	private BookRepository bookRepository;

	public DefaultBookService() {

	}

	public DefaultBookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public void rent(Book book) {
		System.out.println("rent : " + book.getTitle());
	}

	@Override
	public void returnBook(Book book) {
		System.out.println("return : " + book.getTitle());
	}
	
}
