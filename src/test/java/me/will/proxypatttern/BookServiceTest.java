package me.will.proxypatttern;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {

	@Autowired
	private BookService bookService;

	@Test
	void rent() {
		Book book = new Book();
		book.setTitle("spring");
		bookService.rent(book);
	}

}
