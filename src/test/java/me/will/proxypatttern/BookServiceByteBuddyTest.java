package me.will.proxypatttern;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * 클래스 프록시 단점 (CGlib, ByteBuddy)
 * => 상속을 사용하지 못하는 경우 프록시를 만들 수 없다.
 * 1. Private Constrcutor
 * 2. Final class
 * 인터페이스가 있을떄는 인터페이스의 프록시를 만들어 사용할 것!
 */

class BookServiceByteBuddyTest {

	@Test
	void bytebuddy_proxy_test() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		Class<? extends DefaultBookService> proxyClass = new ByteBuddy().subclass(DefaultBookService.class)
				.method(named("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() { // 부가기능 추가(rent())
					DefaultBookService defaultBookService = new DefaultBookService();

					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						System.out.println(" === proxy start === ");
						Object invoke = method.invoke(defaultBookService, args);
						System.out.println(" === proxy end ===");
						return invoke;
					}
				}))
				.make().load(DefaultBookService.class.getClassLoader()).getLoaded();

		DefaultBookService bookService = proxyClass.getConstructor(null).newInstance();

		Book book = new Book();
		book.setTitle("spring");
		bookService.rent(book);
		bookService.returnBook(book);
	}

}
