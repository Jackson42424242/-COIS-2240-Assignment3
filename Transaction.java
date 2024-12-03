package libraryPackage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
public class Transaction {
//Make singleton form task 2.1
	// private static instance
	private static Transaction transaction;

	// constructor
	Transaction(int memberId, int bookId) {
	}

	// get Transaction instance method
	public synchronized static Transaction getTransaction() {
		if (transaction == null) {
			transaction = new Transaction(1,1);
		}
		return transaction;
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
			saveTransaction(transactionDetails);
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
			saveTransaction(transactionDetails);
		} else {
			System.out.println("This book was not borrowed by the member.");
		}
	}

	// Get the current date and time in a readable format
	private String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
//File writer for task 2.2
	public void saveTransaction(String transactionDetails) {
		try (FileWriter writer = new FileWriter("transactions.txt", true)) { // 'true' for appending
            writer.write(transactionDetails + "\n");
            System.out.println("Data written to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
	}

	public static void displayTransactionHistory() 
	{
		//simple file reader
		try (BufferedReader br = new BufferedReader(new FileReader("transactions.txt"))) {
            String l;
            while ((l = br.readLine()) != null) {
                System.out.println(l+"\n"+"------------------------------");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
	}

}
