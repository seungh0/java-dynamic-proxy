package me.will.proxypatttern;

public class BookServiceProxy implements BookService {

	private BookService bookService;

	public BookServiceProxy(BookService bookService) {
		this.bookService = bookService;
	}

	@Override
	public void rent(Book book) {
		System.out.println("====== proxy start ====");
		bookService.rent(book);
		System.out.println("==== proxy end =====");
	}

	@Override
	public void returnBook(Book book) {
		System.out.println("===== proxy start ====");
		bookService.rent(book);
		System.out.println("===== proxy end ====");
	}

	/**
	 * Proxy Pattern
	 * 인터페이스 메소드 만큼 구현해야하는 번거러움 존재.
	 *
	 * => 런타임시에 동적으로 생성해내는 방법 => Dynamic Proxy
	 */

}
