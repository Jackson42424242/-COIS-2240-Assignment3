package libraryPackage;



public class Book {
    public int id;
    public String title;
    public boolean available;
public Exception invalidIDException = new Exception("Invalid Book ID");
    public Book(int id, String title) throws Exception {
        this.id = id;
        this.title = title;
        if(isValidId(id)==true) {
        this.available = true;
    }
        else {throw invalidIDException;}
    }
    // Getter methods
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    // Method to borrow the book
    public void borrowBook() {
        if (available) {
            available = false;
        }
    }

    // Method to return the book
    public void returnBook() {
        available = true;
    }

    // Method to check if a book id is valid
    public boolean isValidId(int id) {
        return id >= 100 && id <= 999;
    }
}
