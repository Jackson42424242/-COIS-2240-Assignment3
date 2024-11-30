import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
//Make singleton form
	// private static instance
	private static Transaction transaction;

	// private constructor
	private Transaction() {
	}

	// get Transaction instance method
	public synchronized static Transaction getTraInstance() {
		if (transaction == null) {
			transaction = new Transaction();
		}
	}

	// Singleton completed
	// Perform the borrowing of a book
	public boolean borrowBook(Book book, Member member) {
		if (book.isAvailable()) {
			book.borrowBook();
			member.borrowBook(book);
			String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed "
					+ book.getTitle();
			System.out.println(transactionDetails);
			return true;
		} else {
			System.out.println("The book is not available.");
			return false;
		}
	}

	// Perform the returning of a book
	public void returnBook(Book book, Member member) {
		if (member.getBorrowedBooks().contains(book)) {
			member.returnBook(book);
			book.returnBook();
			String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned "
					+ book.getTitle();
			System.out.println(transactionDetails);
		} else {
			System.out.println("This book was not borrowed by the member.");
		}
	}

	// Get the current date and time in a readable format
	private String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
}
