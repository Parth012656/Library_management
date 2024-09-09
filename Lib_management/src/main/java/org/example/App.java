package org.example;
import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookServicesV1 BS = new BookServicesV1();

        while (true) {
            System.out.println("Choose an operation: ");
            System.out.println("1. List Issued Books");
            System.out.println("2. Return Book");
            System.out.println("3. Add Book");
            System.out.println("4. Delete Book");
            System.out.println("5. Issue Book");
            System.out.println("6. List All Books");
            System.out.println("7. Return Book with IssuedId");
            System.out.println("8. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // List issued books
                    for (BookIssued bi : BS.IssuedList()) {
                        System.out.println(bi);
                    }
                    break;

                case 2:
                    // Return a book
                    System.out.print("Enter the BookId to return: ");
                    int returnBookId = scanner.nextInt();
                    System.out.print("Enter the IssueId to return: ");
                    int IssueID= scanner.nextInt();
                    BS.returnBook(returnBookId,IssueID);
                    break;

                case 3:
                    // Add a book
                    System.out.print("Enter BookId: ");
                    int bookId = scanner.nextInt();
                    System.out.print("Enter BookName: ");
                    scanner.nextLine();  // Clear the buffer
                    String bookName = scanner.nextLine();
                    System.out.print("Enter BookAuthor: ");
                    String bookAuthor = scanner.nextLine();
                    System.out.print("Enter BookAvailable: ");
                    int bookAvailable = scanner.nextInt();
                    BooksInfo newBook = new BooksInfo(bookId, bookName, bookAuthor, bookAvailable);
                    BS.addBook(newBook);
                    break;

                case 4:
                    // Delete a book
                    System.out.print("Enter the BookId to delete: ");
                    int deleteBookId = scanner.nextInt();
                    BS.deleteBook(deleteBookId);
                    break;

                case 5:
                    // Issue a book
                    System.out.print("Enter BookId: ");
                    int issueBookId = scanner.nextInt();
                    System.out.print("Enter BookName: ");
                    scanner.nextLine();  // Clear the buffer
                    String issueBookName = scanner.nextLine();
                    System.out.print("Enter Person's Name: ");
                    String personName = scanner.nextLine();
                    System.out.print("Enter Mobile Number: ");
                    String mobileNumber = scanner.nextLine();
                    BookIssued bookIssued = new BookIssued(issueBookId, issueBookName, personName, 1, mobileNumber);
                    BS.issueBook(issueBookId, bookIssued);
                    break;

                case 6:
                    // List all books
                    for (BooksInfo bi : BS.booklist()) {
                        System.out.println(bi);
                    }
                    break;

                case 7:
                    // Return a book with IssuedId
                    System.out.print("Enter BookId: ");
                    int returnBookId2 = scanner.nextInt();
                    System.out.print("Enter IssuedId: ");
                    int returnIssuedId = scanner.nextInt();
                    BS.returnBook(returnBookId2, returnIssuedId);
                    break;

                case 8:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
