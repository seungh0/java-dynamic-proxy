package me.will.proxypatttern;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Dynamic Proxy
 *
 * 매번 클래스를 생성해야하는 문제점은 해결할 수 있다.
 * but) InvocationHandler() 코드가 지저분해질 수 있음.
 *
 * => 그래서 나온것이 Spring AOP
 */

class BookServiceTest {

	private BookService bookServiceProxy = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class}, new InvocationHandler() {

		BookService bookService = new DefaultBookService();

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// rent()에만 적용하고 싶다면
			if (!method.getName().equals("rent")) {
				return method.invoke(bookService, args);
			}

			System.out.println("aaaa"); // 부가기능 추가
			Object invoke = method.invoke(bookService, args);
			System.out.println("bbbb"); // 부가 기능 추가
			return invoke;
		}

//		@Override
//		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//			return method.invoke(bookService, args); // DefaultBookService만 하는 역할
//		}
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
