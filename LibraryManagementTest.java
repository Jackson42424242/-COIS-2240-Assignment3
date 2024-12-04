package libraryPackage;

import java.lang.reflect.Constructor;
import org.junit.Before;
import org.junit.Test;
import java.lang.reflect.Modifier;
import static org.junit.Assert.*;

public class LibraryManagementTest {
	public Library library;

	@Before

	public void setUp() throws Exception {

		library = new Library();
	}

	@Test
	public void testBookId() throws Exception {
		try {
			// Test id ranges
			Book book1 = new Book(100, "Boundary Case 1");
			assertTrue(book1.isValidId(100)); // 100 is valid

			Book book2 = new Book(999, "Boundary Case 2");
			assertTrue(book2.isValidId(999)); // 999 is valid

			Book book3 = new Book(1000, "Invalid Case");
			assertFalse(book3.isValidId(1000)); // 1000 is invalid

			// Test invalid numbers
			Book book4 = new Book(99, "Invalid Case");
			assertFalse(book4.isValidId(99)); // Less than 100 is invalid

			Book book5 = new Book(1001, "Invalid Case");
			assertFalse(book5.isValidId(1001)); // Greater than 999 is invalid

		} catch (Exception e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}

	@Test
	public void testBorrowReturn() throws Exception {
		Book book = new Book(100, "Test book");
		Member member = new Member(1111, "Test member");
		// Borrow
		assertTrue("Book should be borrowed successfully", Transaction.getTransaction().borrowBook(book, member));

		// Check book availability
		assertFalse("Book should no longer be available", book.isAvailable());
		assertTrue("Member should have borrowed the book", member.getBorrowedBooks().contains(book));

		// Return
		Transaction.getTransaction().returnBook(book, member);

		// The book is available again
		assertTrue("Book should be available after return", book.isAvailable());
		assertFalse("Member should no longer have the book", member.getBorrowedBooks().contains(book));
	}

	@Test
	public void testSingletonTransaction() throws Exception {
		try {
			Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
			  // Check if the constructor is private
	        int modifiers = constructor.getModifiers();
	        assertTrue("Constructor should be private", Modifier.isPrivate(modifiers));
		
		} catch (Exception e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}
}