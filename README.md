# Library Management System

## Project Description

The **Library Management System** is a comprehensive application designed to manage books in a library. This project enables users to manage book availability, issue books to users, and keep track of returns. It leverages a simple SQL database for storing information about books, book issuance, and user details. The project uses Java and MySQL for the backend, with JDBC for database interaction.

---

## Features

1. **Book Management:**
   - Add new books to the library.
   - Remove existing books.
   - Update book availability and issuance status.

2. **Book Issuance:**
   - Issue books to library users.
   - Track books issued to different users.
   - Automatically update the available count of books upon issuance.
   
3. **Book Return:**
   - Return books and update the issued book status.
   - Delete the issued book entry upon return.
   - Increase the available book count in the library.

4. **Book Availability Tracking:**
   - Check how many copies of a book are available.
   - Keep track of the number of books issued.

---

## Database Structure

The project uses two main tables:

### `LibraryInfo`
Stores details about books in the library, including the available and issued copies.

| Column        | Type         | Description                         |
| ------------- | ------------ | ----------------------------------- |
| `BookId`      | INT          | Unique identifier for each book     |
| `BookName`    | VARCHAR(50)  | Name of the book                    |
| `BookAuthor`  | VARCHAR(50)  | Author of the book                  |
| `BookAvailable` | INT        | Number of copies available          |
| `BookIssued`  | INT          | Number of copies issued             |

### `BooksIssued`
Stores details of books issued to users.

| Column        | Type         | Description                         |
| ------------- | ------------ | ----------------------------------- |
| `IssuedId`    | INT (AUTO_INCREMENT) | Unique identifier for each issued book record |
| `BookId`      | INT          | Foreign key referencing `LibraryInfo` |
| `BookName`    | VARCHAR(50)  | Name of the book                    |
| `Person`      | VARCHAR(50)  | Name of the person who issued the book |
| `BookIssued`  | INT          | Number of copies issued             |
| `MobileNumber`| VARCHAR(10)  | Contact number of the person        |
| `IssuedDate`  | DATE         | Date of issuance                    |
| `ReturningDate`| DATE        | Expected return date                |

---

## Project Structure

- **Java Code:** Implements business logic and manages interaction with the database using JDBC.
- **MySQL Database:** Stores book and user data for issuing and returning books.
  
**Key Methods:**
- `addBook(BooksInfo booksInfo)`: Add new books to the library.
- `issueBook(int BookId, BookIssued bookIssued)`: Issue books to users and update availability.
- `returnBook(int BookId)`: Return a book and update the library inventory.

---

## Setup Instructions

1. **Database Setup:**
   - Import the SQL file provided to set up the `LibraryInfo` and `BooksIssued` tables.
   - Update your database connection details in the `ConnectionDetails` class in Java.

2. **Running the Project:**
   - Compile the Java project and run the main application.
   - Use the provided methods to manage the library's book collection and issuance records.

---

## Example Queries

- Insert a new book:
  ```sql
  INSERT INTO LibraryInfo (BookId, BookName, BookAuthor, BookAvailable, BookIssued) VALUES (11, 'JJK', 'Me', 4, 1);
