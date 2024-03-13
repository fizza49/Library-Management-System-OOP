# Library Management System

## Description

The Library Management System is a console-based application implemented in Java. It provides a simple interface for managing books and users in a library using error handling and file handling.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed
- Java IDE (I personally use Visual Studio Code).This is optional you can use command prompt to run the program if you have JDK installed.

### Setup and Run

1. Clone the repository:
   git clone https://github.com/fizza49/Library-Management-System-OOP/tree/main
   
2. Navigate to the project directory:
cd library-management-system

4. Compile the Java code:
javac librarymanagementsystem.java

4.Run the compiled code:
java librarymanagementsystem

## Key Features

1. Add Book:
Add new books to the library by providing Book ID, Title, Author, and Genre.

2.Add User:
Add new users to the library by providing User ID, Name, and Contact Information.

3.Display Books:
View the list of available books in the library.

4.Borrow a Book:
Allow users to borrow books, updating book availability and user's borrowed books.

5.Return a Book:
Enable users to return books, updating book availability and user's borrowed books.

6.Search Books:
Search for books by Title or Author or UserID, providing information about the book if found.

7.Display all the books:
Display all the books in the library with the status Available or Not Available.

## File Handling

Save and load the library state using serialization. The library data is stored in library.dat.
