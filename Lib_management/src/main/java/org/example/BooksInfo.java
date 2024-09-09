package org.example;

public class BooksInfo {
    private String BookName;
    private String BookAuthor;
    private int BookId;
    private int Available;
    private int Issued;



    BooksInfo(){};


    public BooksInfo(int BookId,String BookName,String BookAuthor,int Available,int issued){
        this.BookId=BookId;
        this.BookName=BookName;
        this.BookAuthor=BookAuthor;
        this.Available=Available;
        this.Issued=issued;
    }

    public BooksInfo(int bookId,String bookName, String bookAuthor,  int available) {
        BookName = bookName;
        BookAuthor = bookAuthor;
        BookId = bookId;
        Available = available;
    }

    public void setBookId(int BookId){
        this.BookId=BookId;
    }
    public int getBookId(){
        return BookId;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getBookAuthor() {
        return BookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        BookAuthor = bookAuthor;
    }

    public int getAvailable() {
        return Available;
    }
    public int getIssued() {
        return Issued;
    }

    public void setIssued(int issued) {
        Issued = issued;
    }

    public void setAvailable(int available) {
        Available = available;
    }

    @Override
    public String toString() {
        // Formatting the output using String.format for consistent width for each column
        return String.format("{ BookId=%-5d | BookName=%-30s | BookAuthor=%-20s | Available=%-2d | Issued=%-2d }",
                BookId, BookName, BookAuthor, Available, Issued);
    }
}
