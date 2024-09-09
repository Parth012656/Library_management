package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

  class BookServicesV1 {

      public void addBook(BooksInfo booksInfo){
          final String addQuery="insert into LibraryInfo (BookId, BookName, BookAuthor, BookAvailable) values (?,?,?,?)";
          try {
              Connection connection = ConnectionDetails.getConnection();
              PreparedStatement preparedStatement=connection.prepareStatement(addQuery);
              preparedStatement.setInt(1,booksInfo.getBookId());
              preparedStatement.setString(2,booksInfo.getBookName());
              preparedStatement.setString(3,booksInfo.getBookAuthor());
              preparedStatement.setInt(4,booksInfo.getAvailable());
              int update=preparedStatement.executeUpdate();
              System.out.println("Book Inserted : "+update);
          }
          catch (SQLException s){
              System.out.println(s);
          }
      }

      List<BooksInfo> booklist(){
          final String listQuery="Select * from LibraryInfo";
          List<BooksInfo> bookList=new ArrayList<>();
          try {
              Connection connection=ConnectionDetails.getConnection();
              Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
              ResultSet BooksTable=statement.executeQuery(listQuery);
              while (BooksTable.next())
              {
                  int BookId=BooksTable.getInt("BookId");
                  String BookName=BooksTable.getString("BookName");
                  String BookAuthor=BooksTable.getString("BookAuthor");
                  int BookAvailable=BooksTable.getInt("BookAvailable");
                  int BookIssued=BooksTable.getInt("BookIssued");
                  BooksInfo booksInfo=new BooksInfo(BookId,BookName,BookAuthor,BookAvailable,BookIssued);
                  bookList.add(booksInfo);
              }



          } catch (SQLException e) {
              e.printStackTrace();
          }
          return bookList;
      }

      void deleteBook(int id) {
          // Query to check the availability of the book
          final String checkAvailabilityQuery = "SELECT BookAvailable FROM LibraryInfo WHERE BookId = ?";
          // Query to update the availability of the book
          final String updateAvailabilityQuery = "UPDATE LibraryInfo SET BookAvailable = ? WHERE BookId = ?";
          // Query to delete the book
          final String deleteBookQuery = "DELETE FROM LibraryInfo WHERE BookId = ?";

          try (Connection connection = ConnectionDetails.getConnection();
               PreparedStatement checkStmt = connection.prepareStatement(checkAvailabilityQuery);
               PreparedStatement updateStmt = connection.prepareStatement(updateAvailabilityQuery);
               PreparedStatement deleteStmt = connection.prepareStatement(deleteBookQuery)) {

              // Check the availability of the book
              checkStmt.setInt(1, id);
              ResultSet resultSet = checkStmt.executeQuery();
              if (resultSet.next()) {
                  int available = resultSet.getInt("BookAvailable");
                  if (available > 1) {
                      // Update the availability if more than one copy is available
                      updateStmt.setInt(1, available - 1);
                      updateStmt.setInt(2, id);
                      int rowsUpdated = updateStmt.executeUpdate();
                      System.out.println(rowsUpdated + " book availability updated");
                  } else {
                      // Delete the book if no copies are available
                      deleteStmt.setInt(1, id);
                      int rowsDeleted = deleteStmt.executeUpdate();
                      System.out.println("Book Deleted: " + rowsDeleted);
                  }
              }
          } catch (SQLException s) {
              s.printStackTrace();
          }
      }


      public void issueBook(int BookId, BookIssued bookIssued) {
          final String fetchDetailsQuery = "SELECT BookAvailable, BookIssued FROM LibraryInfo WHERE BookId = ?";
          final String updateLibraryInfoQuery = "UPDATE LibraryInfo SET BookAvailable = ?, BookIssued = ? WHERE BookId = ?";
          final String insertIssueQuery = "INSERT INTO BooksIssued (BookId, BookName, Person, BookIssued, MobileNumber) VALUES (?, ?, ?, ?, ?)";

          try {
              Connection connection = ConnectionDetails.getConnection();

              // Fetch BookAvailable and BookIssued details
              PreparedStatement fetchDetailsStmt = connection.prepareStatement(fetchDetailsQuery);
              fetchDetailsStmt.setInt(1, BookId);
              ResultSet resultSet = fetchDetailsStmt.executeQuery();

              if (resultSet.next()) {
                  int available = resultSet.getInt("BookAvailable");
                  int issued = resultSet.getInt("BookIssued");

                  // Check if book is available
                  if (available > 0) {
                      // Update the LibraryInfo table
                      PreparedStatement updateLibraryInfoStmt = connection.prepareStatement(updateLibraryInfoQuery);
                      updateLibraryInfoStmt.setInt(1, available - 1);
                      updateLibraryInfoStmt.setInt(2, issued + 1);
                      updateLibraryInfoStmt.setInt(3, BookId);
                      updateLibraryInfoStmt.executeUpdate();

                      // Insert into BooksIssued table
                      PreparedStatement insertIssueStmt = connection.prepareStatement(insertIssueQuery);
                      insertIssueStmt.setInt(1, bookIssued.getBookid());
                      insertIssueStmt.setString(2, bookIssued.getBname());
                      insertIssueStmt.setString(3, bookIssued.getIname());
                      insertIssueStmt.setInt(4, bookIssued.getIssue());
                      insertIssueStmt.setString(5, bookIssued.getMobile());
                      int rowsInserted = insertIssueStmt.executeUpdate();

                      System.out.println("Book issued successfully, rows affected: " + rowsInserted);

                      // Close the prepared statements
                      updateLibraryInfoStmt.close();
                      insertIssueStmt.close();
                  } else {
                      System.out.println("Book is not available.");
                  }
              }

              // Close resources
              fetchDetailsStmt.close();
              resultSet.close();

          } catch (SQLException e) {
              e.printStackTrace();
          }
      }


      //Book Issued List
      public List<BookIssued> IssuedList(){
          final String IssueTableQuery="Select * from BooksIssued";
          List<BookIssued> IssueList=new ArrayList<>();
          try{
              Connection connection=ConnectionDetails.getConnection();
              Statement statement=connection.createStatement();
              ResultSet IssueTable=statement.executeQuery(IssueTableQuery);
              while (IssueTable.next()){
                  int IssuedId=IssueTable.getInt("IssuedId");
                  int BookId=IssueTable.getInt("BookId");
                  String BookName=IssueTable.getString("BookName");
                  String Person=IssueTable.getString("Person");
                  int BookIsuued=IssueTable.getInt("BookIssued");
                  String mobileNumber=IssueTable.getString("MobileNumber");
                  String issuedDate=IssueTable.getString("IssuedDate");
                  String returningDate=IssueTable.getString("ReturningDate");
                  BookIssued bookIssued=new BookIssued(IssuedId,BookId, BookName, Person, BookIsuued, mobileNumber,issuedDate,returningDate);
                  IssueList.add(bookIssued);
              }
          }
          catch (SQLException s){
              s.printStackTrace();
          }
          return IssueList;
      }


      //Method to return book
//      public void returnBook(int id)
//      {
//          final String deleteQuery="DELETE FROM BooksIssued WHERE BookId = ?";
//          final String updateQuery="UPDATE LibraryInfo SET BookAvailable = ?,BookIssued = ? WHERE BookId = ?";
//          final String fetchDetailsQuery="SELECT BookAvailable, BookIssued from LibraryInfo where BookId = ?";
//          try{
//              Connection connection=ConnectionDetails.getConnection();
//              PreparedStatement deletePstmnt=connection.prepareStatement(deleteQuery);
//              deletePstmnt.setInt(1,id);
//              Statement statement=connection.createStatement();
//              ResultSet fetchDetails=statement.executeQuery(fetchDetailsQuery);
//              if(fetchDetails.next())
//              {
//                  int avail=fetchDetails.getInt("BookAvailable");
//                  int issue=fetchDetails.getInt("BookIssued");
//                  PreparedStatement updateLibInfo=connection.prepareStatement(updateQuery);
//                  updateLibInfo.setInt(1,avail+1);
//                  updateLibInfo.setInt(2,issue-1);
//                  updateLibInfo.setInt(3,id);
//                  int deleted=updateLibInfo.executeUpdate();
//                  System.out.println(deleted+"Book Returned");
//              }
//
//          }
//          catch (SQLException s)
//          {
//              s.printStackTrace();
//          }
//      }
      public void returnBook(int Bookid,int IssueId) {
          final String deleteQuery = "DELETE FROM BooksIssued WHERE IssuedId = ?";
          final String updateQuery = "UPDATE LibraryInfo SET BookAvailable = ?, BookIssued = ? WHERE BookId = ?";
          final String fetchDetailsQuery = "SELECT BookAvailable, BookIssued FROM LibraryInfo WHERE BookId = ?";

          try (Connection connection = ConnectionDetails.getConnection();
               PreparedStatement deletePstmnt = connection.prepareStatement(deleteQuery);
               PreparedStatement fetchPstmnt = connection.prepareStatement(fetchDetailsQuery);
               PreparedStatement updateLibInfo = connection.prepareStatement(updateQuery)) {

              // Delete the issued book record
              deletePstmnt.setInt(1, IssueId);
              int deleted = deletePstmnt.executeUpdate();

              if (deleted > 0) {
                  // Fetch the current details of the book
                  fetchPstmnt.setInt(1, Bookid);
                  ResultSet fetchDetails = fetchPstmnt.executeQuery();

                  if (fetchDetails.next()) {
                      int avail = fetchDetails.getInt("BookAvailable");
                      int issue = fetchDetails.getInt("BookIssued");

                      // Update the library info
                      updateLibInfo.setInt(1, avail + 1); // Increase available count
                      updateLibInfo.setInt(2, issue - 1); // Decrease issued count
                      updateLibInfo.setInt(3, Bookid);

                      int updated = updateLibInfo.executeUpdate();
                      System.out.println(updated + " Book Returned");
                  }
                  fetchDetails.close();
              } else {
                  System.out.println("No record found to delete.");
              }

          } catch (SQLException e) {
              e.printStackTrace();
          }
      }


  }
