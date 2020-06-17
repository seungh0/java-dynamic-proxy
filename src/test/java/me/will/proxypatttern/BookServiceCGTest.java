package me.will.proxypatttern;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

class BookServiceCGTest {


	/**
	 * CGlib를 사용한 클래스를 프록시 생성
	 */
	@Test
	void cglib_proxy_test() {
		MethodInterceptor handler = new MethodInterceptor() {
			DefaultBookService defaultBookService = new DefaultBookService();

			@Override
			public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
				if (!method.getName().equals("rent")) {
					return method.invoke(defaultBookService, args);
				}
				System.out.println(" === proxy start ===");
				Object invoke = method.invoke(defaultBookService, args);
				System.out.println("==== proxy end === ");
				return invoke;
			}
		};

		DefaultBookService bookService = (DefaultBookService) Enhancer.create(DefaultBookService.class, handler);

		Book book = new Book();
		book.setTitle("spring");
		bookService.rent(book);
		bookService.returnBook(book);
	}

}
