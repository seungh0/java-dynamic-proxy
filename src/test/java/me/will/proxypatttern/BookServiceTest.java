package me.will.proxypatttern;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class BookServiceTest {

	private BookService bookServiceProxy = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class}, new InvocationHandler() {

		BookService bookService = new DefaultBookService();

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			return method.invoke(bookService, args); // DefaultBookService만 하는 역할
		}
	});

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
