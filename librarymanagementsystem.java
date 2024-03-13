import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

//the book class
class Book implements Serializable {
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private boolean available;
    
    //the constructor for book class

    public Book(int bookID, String title, String author, String genre, boolean available) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
    }

    // Getter and setter methods for book class

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

//the user class 
class User implements Serializable {
    private int userID;
    private String name;
    private String contactInfo;
    private ArrayList<Book> borrowedBooks;

    //the constructor for user class

    public User(int userID, String name, String contactInfo) {
        this.userID = userID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.borrowedBooks = new ArrayList<>();
    }

    // Getter and setter methods for user class

    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }
      
}

//the library class
class Library implements Serializable{
    private ArrayList<Book> books;    //using array list for storing books and users 
    private ArrayList<User> users;

    //the constructor for library class
    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

   //method to add a book to the library through book class
    public void addBook(Book book) {
        books.add(book);
    }

    //method to add auser to the library through user class
    public void addUser(User user) {
        users.add(user);
    }

    //method to display all the books 
    public void displayBooks() {
        for (Book book : books) {
            System.out.println("ID: " + book.getBookID() + ", Title: " + book.getTitle() +
                    ", Author: " + book.getAuthor() + ", Genre: " + book.getGenre() +
                    ", Available: " + (book.isAvailable() ? "Yes" : "No"));
        }
    }

    //method to search the book by its title
    public Book searchBookByTitle(String title) {
        for (Book book : books) {
         //the equalsIgnoreCase  compares the getauthor string and author string ignoring the uppercase and lowercase
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    //method to search the book by thr author
    public Book searchBookByAuthor(String author) {
        for (Book book : books) {
            //the equalsIgnoreCase  compares the getauthor string and author string ignoring the uppercase and lowercase
            //if found it returns the books
            if (book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        return null;
    }

    //method to serach the user by his/her ID
    public User searchUserByID(int userID) {
        for (User user : users) {
            if (user.getUserID() == userID) {
                return user;
            }
        }
        return null;
    }
}

class FileHandler {
    public static void saveLibrary(Library library, String fileName) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(library);
        } catch (IOException e) {
            System.out.println("Error saving library: " + e.getMessage());
        }
    } 

    public static Library loadLibrary(String fileName) {
        Library library = new Library();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            library = (Library) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading library: " + e.getMessage());
        }
        return library;
    }
}

public class librarymanagementsystem {
   
    public static void main(String[] args) {
        // Load the library data from the file
        Library library = FileHandler.loadLibrary("library.dat");
        // Create a scanner for user input
        Scanner scanner = new Scanner(System.in);
        // Save the initial state of the library to the file
        FileHandler.saveLibrary(library, "library.dat");

        while (true) {
            // Display the main menu
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add User");
            System.out.println("3. Display Books");
            System.out.println("4. Borrow or check out a Book");
            System.out.println("5. Return Book");
            System.out.println("6. Search Books by Title or Author");
            System.out.println("7. Search Book by user ID ");
            System.out.println("8. Exit");

            // Get user choice
            System.out.print("Enter your choice: ");
            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        // Adding a new book
                        try {
                            boolean validInput = false;
                            int bookID = 0;

                            // Validate and get Book ID
                            while (!validInput) {
                                System.out.print("Enter Book ID: ");
                                if (scanner.hasNextInt()) {
                                    bookID = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline
                                    validInput = true;
                                } else {
                                    System.out.println("Enter a valid integer");
                                    scanner.nextLine(); // Consume the invalid input
                                }
                            }

                            // Get other book details
                            System.out.print("Enter Title: ");
                            String title = scanner.nextLine();
                            System.out.print("Enter Author: ");
                            String author = scanner.nextLine();
                            System.out.print("Enter Genre: ");
                            String genre = scanner.nextLine();

                            // Create a new Book object and add it to the library
                            Book newBook = new Book(bookID, title, author, genre, true);
                            library.addBook(newBook);
                            System.out.println("Book added successfully!");
                        } catch (Exception e) {
                            System.out.println("Error adding book: " + e.getMessage());
                            continue;
                        }
                        break;

                    case 2:
                        // Adding a new user
                        try {
                            boolean validInput = false;
                            int userID = 0;

                            // Validate and get User ID
                            while (!validInput) {
                                System.out.print("Enter User ID: ");
                                if (scanner.hasNextInt()) {
                                    userID = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline
                                    validInput = true;
                                } else {
                                    System.out.println("Enter a valid integer");
                                    scanner.nextLine(); // Consume the invalid input
                                }
                            }

                            // Get other user details
                            System.out.print("Enter Name: ");
                            String name = scanner.nextLine();
                            System.out.print("Enter Contact Information: ");
                            String contactInfo = scanner.nextLine();

                            // Create a new User object and add it to the library
                            User newUser = new User(userID, name, contactInfo);
                            library.addUser(newUser);
                            System.out.println("User added successfully!");
                        } catch (Exception e) {
                            System.out.println("Error adding user: " + e.getMessage());
                        }
                        break;

                    case 3:
                        // Displaying books
                        library.displayBooks();
                        break;

                    case 4:
                        // Borrowing a book
                        try {
                            boolean validInput = false;
                            int borrowUserID = 0;

                            // Validate and get User ID
                            while (!validInput) {
                                System.out.print("Enter User ID: ");
                                if (scanner.hasNextInt()) {
                                    borrowUserID = scanner.nextInt();
                                    scanner.nextLine(); // Consume the newline
                                    validInput = true;
                                } else {
                                    System.out.println("Enter a valid integer");
                                    scanner.nextLine(); // Consume the invalid input
                                }
                            }

                            // Get Book Title
                            System.out.print("Enter Book Title: ");
                            String borrowBookTitle = scanner.nextLine();

                            // Search for the user and book, then borrow the book
                            User borrower = library.searchUserByID(borrowUserID);
                            Book bookToBorrow = library.searchBookByTitle(borrowBookTitle);

                            if (borrower != null && bookToBorrow != null && bookToBorrow.isAvailable()) {
                                borrower.borrowBook(bookToBorrow);
                                bookToBorrow.setAvailable(false);
                                System.out.println("Book borrowed successfully!");
                            } else {
                                System.out.println("Error borrowing book. Please check user and book availability.");
                            }
                        } catch (Exception e) {
                            System.out.println("Error borrowing book: " + e.getMessage());
                        }
                        // Save the library state after the operation
                        FileHandler.saveLibrary(library, "library.dat");
                        break;

                    case 5:
                        // Returning a book
                        try {
                            // Get User ID
                            System.out.print("Enter User ID: ");
                            int returnUserID = 0;

                            if (scanner.hasNextInt()) {
                                returnUserID = scanner.nextInt();
                                scanner.nextLine(); // Consume the newline
                            } else {
                                System.out.println("Enter a valid integer");
                                scanner.nextLine(); // Consume the invalid input
                                continue; // Go back to the main loop
                            }

                            // Get Book Title
                            System.out.print("Enter Book Title: ");
                            String returnBookTitle = scanner.nextLine();

                            // Search for the user and book, then return the book
                            User returner = library.searchUserByID(returnUserID);
                            Book bookToReturn = library.searchBookByTitle(returnBookTitle);

                            if (returner != null && bookToReturn != null && returner.getBorrowedBooks().contains(bookToReturn)) {
                                returner.returnBook(bookToReturn);
                                bookToReturn.setAvailable(true);
                                System.out.println("Book returned successfully!");
                            } else {
                                System.out.println("Book not found or not borrowed by the user.");
                            }
                            // Save the library state after the operation
                            FileHandler.saveLibrary(library, "library.dat");
                        } catch (Exception e) {
                            System.out.println("Error returning book: " + e.getMessage());
                        }
                        break;

                    case 6:
                        // Searching for books by title or author
                        System.out.print("Enter Title or Author: ");
                        scanner.nextLine(); // Consume the newline
                        String search = scanner.nextLine();

                        // Search for the book by title or author
                        Book searchedBook = library.searchBookByTitle(search);
                        if (searchedBook == null) {
                            searchedBook = library.searchBookByAuthor(search);
                        }

                        if (searchedBook != null) {
                            System.out.println("Book found: " + searchedBook.getTitle() + " by " + searchedBook.getAuthor());
                        } else {
                            System.out.println("Book not found.");
                        }
                        // Save the library state after the operation
                        FileHandler.saveLibrary(library, "library.dat");
                        break;

                    case 7:
                        // Searching for a user by User ID
                        System.out.println("Enter the UserID: ");
                        scanner.nextLine(); // Consume the newline
                        int searchUserID = scanner.nextInt();

                        // Search for the user by User ID
                        User searchById = library.searchUserByID(searchUserID);
                        if (searchById != null) {
                            System.out.println("User found " + searchById.getUserID());
                        } else {
                            System.out.println("User not found.");
                        }
                        // Save the library state after the operation
                        FileHandler.saveLibrary(library, "library.dat");
                        break;

                    case 8:
                        // Exiting the program
                        System.out.println("Exiting Library Management System. Goodbye!");
                        // Save the final state of the library before exiting
                        FileHandler.saveLibrary(library, "library.dat");
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            } catch (Exception e) {
                // Handle invalid input
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
                continue; // Go back to the main loop
            }
        }
    }
}
