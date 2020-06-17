package me.will.proxypatttern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public void rent(Book book) {
		System.out.println("rent : " + book.getTitle());
	}

}
