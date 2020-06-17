package me.will.proxypatttern;

import org.junit.jupiter.api.Test;

class BookServiceTest {

	private BookService bookServiceProxy  = new BookServiceProxy(new DefaultBookService());

	@Test
	void rent() {
		Book book = new Book();
		book.setTitle("spring");
		bookServiceProxy.rent(book);
	}

	@Test
	void returnTest() {
		Book book = new Book();
		book.setTitle("spring");
		bookServiceProxy.returnBook(book);
	}

}
