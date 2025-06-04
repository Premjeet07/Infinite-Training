package com.java.lib.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.lib.model.AdminLogin;
import com.java.lib.model.Books;
import com.java.lib.model.LibUsers;
import com.java.lib.model.TranBook;
import com.java.lib.model.TranReturn;
import com.java.lib.util.ConnectionHelper;
import com.java.lib.util.EncryptPassword;

public class LibraryDaoImpl implements LibraryDao {

	Connection connection;
	PreparedStatement psmt;
	
	
	
	public int issueOrNot(String userName, int bookId) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String sql = "select count(*) cnt from TranBook where UserName=? and BookId=?";
		psmt = connection.prepareStatement(sql);
		psmt.setString(1, userName);
		psmt.setInt(2, bookId);
		ResultSet rs = psmt.executeQuery();
		rs.next();
		int count =rs.getInt("cnt");
		return count;
	}
	
	@Override
	public String createUser(LibUsers libUsers) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(libUsers.getPassWord());
		connection = ConnectionHelper.getConnection();
		String cmd = "Insert into LibUsers(UserName,Password) values(?,?)";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, libUsers.getUserName());
		psmt.setString(2, encr);
		psmt.executeUpdate();
		return "User Account Created Successfully...";
	}

	@Override
	public int login(LibUsers libUsers) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(libUsers.getPassWord());
		connection = ConnectionHelper.getConnection();
		String cmd = "select count(*) cnt from LibUsers where UserName = ? AND "
				+ " Password = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, libUsers.getUserName());
		psmt.setString(2, encr);
		ResultSet rs = psmt.executeQuery();
		rs.next();
		int count= rs.getInt("cnt");
		return count;
	}

	@Override
	public List<Books> searchBooks(String searchType, String searchValue) throws ClassNotFoundException, SQLException {
		String cmd;
		boolean isValid = true;
		if(searchType.equals("id")) {
			cmd = " SELECT * FROM Books WHERE Id = ? " ;
		} else if (searchType.equals("bookname")) {
			cmd = " SELECT * FROM Books Where Name = ?";
		} else if (searchType.equals("authorname")) {
			cmd = "SELECT * FROM Books where Author = ?";
		} else if (searchType.equals("dept")) {
			cmd = "select * from Books where Dept = ?";
		} else {
			isValid = false;
			cmd = "select * from Books";
		}
		connection = ConnectionHelper.getConnection();
		psmt = connection.prepareStatement(cmd);
		if (isValid == true) {
			psmt.setString(1, searchValue);
		}
		ResultSet rs = psmt.executeQuery();
		Books books = null;
		List<Books> booksList = new ArrayList<Books>();
		while(rs.next()) {
			books = new Books();
			books.setId(rs.getInt("id"));
			books.setName(rs.getString("name"));
			books.setAuthor(rs.getString("author"));
			books.setEdition(rs.getString("edition"));
			books.setDept(rs.getString("dept"));
			books.setNoOfBooks(rs.getInt("TotalBooks"));
			booksList.add(books);
		}
		return booksList;
	}

	@Override
	public String issueBook(String userName, int bookId) throws ClassNotFoundException, SQLException {
		int count = issueOrNot(userName, bookId);
		if (count==0) {
			connection = ConnectionHelper.getConnection();
			String sql = "Insert into TranBook(UserName,BookId) values(?,?)";
			psmt = connection.prepareStatement(sql);
			psmt.setString(1, userName);
			psmt.setInt(2, bookId);
			psmt.executeUpdate();
			sql="Update Books set TotalBooks=TotalBooks-1 where id=?";
			psmt = connection.prepareStatement(sql);
			psmt.setInt(1, bookId);
			psmt.executeUpdate();
			return "Book with Id " +bookId + " Issued Successfully...";
		} else {
			return "Book Id " +bookId+ " for User " +userName + " Already Issued...";
		}
	}

	@Override
	public List<TranBook> accountDetails(String userName) throws ClassNotFoundException, SQLException {
		connection = ConnectionHelper.getConnection();
		String cmd = "select * from tranbook where username = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, userName);
		ResultSet rs = psmt.executeQuery();
		List<TranBook> booksIssued = new ArrayList<TranBook>();
		TranBook tranBook = null;
		while(rs.next()) {
			tranBook = new TranBook();
			tranBook.setBookId(rs.getInt("BookId"));
			tranBook.setUserName(rs.getString("UserName"));
			tranBook.setFromDate(rs.getDate("FromDate"));
			booksIssued.add(tranBook);
		}
		return booksIssued;
	}

	@Override
	public String returnBook(String userName, int bookId) throws ClassNotFoundException, SQLException {
		String cmd = "SELECT * FROM TranBook WHERE Username = ? and BookId = ?";
		connection = ConnectionHelper.getConnection();
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, userName);
		psmt.setInt(2, bookId);
		ResultSet rs = psmt.executeQuery();
		rs.next();
		Date fromDate = rs.getDate("fromDate");
		
		String sql2 = " INSERT INTO TransReturn(UserName,BookId,FromDate) VALUES (?,?,?)" ;
		psmt = connection.prepareStatement(sql2);
		psmt.setString(1,userName);
		psmt.setInt(2,bookId);
		psmt.setDate(3,fromDate);
		psmt.executeUpdate();
		
		String sql1 = "DELETE FROM TranBook WHERE BookId = ? AND Username = ? " ;
		psmt = connection.prepareStatement(sql1);
		psmt.setInt(1,bookId);
		psmt.setString(2,userName);
		psmt.executeUpdate();
		
		String sql3 = "Update Books set TotalBooks = TotalBooks + 1 where Id = ?";
		psmt = connection.prepareStatement(sql3);
		psmt.setInt(1, bookId);
		psmt.executeUpdate();
		return "Book with Id " +bookId + " For User " +userName + " Returned Successfully...";
	}

	@Override
	public List<TranReturn> returnHistory() throws ClassNotFoundException, SQLException {
		List<TranReturn> historyList = new ArrayList<>();
        String sql = "SELECT * FROM TransReturn";
 
        try (
            Connection conn = ConnectionHelper.getConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery()
        ) {
            while (rs.next()) {
                TranReturn tr = new TranReturn();
                tr.setUsername(rs.getString("Username"));
                tr.setBookId(rs.getInt("BookId"));
                tr.setFromDate(rs.getDate("Fromdate"));
                tr.setToDate(rs.getDate("Todate"));
                historyList.add(tr);
            }
        }
 
        return historyList;
	}

	@Override
	public String CreateAdmin(AdminLogin adminLogin) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(adminLogin.getPasscode());
		connection = ConnectionHelper.getConnection();
		String cmd = "Insert into AdminLogin(username,passcode) values(?,?)";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, adminLogin.getUsername());
		psmt.setString(2, encr);
		psmt.executeUpdate();
		return "Admin Account Created Successfully...";
	}

	@Override
	public int AdminSignup(AdminLogin adminLogin) throws ClassNotFoundException, SQLException {
		String encr = EncryptPassword.getCode(adminLogin.getPasscode());
		connection = ConnectionHelper.getConnection();
		String cmd = "select count(*) cnt from AdminLogin where username = ? AND "
				+ " Passcode = ?";
		psmt = connection.prepareStatement(cmd);
		psmt.setString(1, adminLogin.getUsername());
		psmt.setString(2, encr);
		ResultSet rs = psmt.executeQuery();
		rs.next();
		int count= rs.getInt("cnt");
		return count;
	}

	@Override
	public String addOrUpdateBook(Books book) throws ClassNotFoundException, SQLException {
	    connection = ConnectionHelper.getConnection();
	    
	    // Check if a book with the same Name and Author already exists
	    String checkExistSql = "SELECT COUNT(*) AS cnt FROM Books WHERE Name = ? AND Author = ?";
	    psmt = connection.prepareStatement(checkExistSql);
	    psmt.setString(1, book.getName());
	    psmt.setString(2, book.getAuthor());
	    ResultSet rs = psmt.executeQuery();
	    rs.next();
	    
	    int count = rs.getInt("cnt");
	    
	    if (count == 0) {
	        // Book doesn't exist, so we insert a new book
	        String insertSql = "INSERT INTO Books (Name, Author, Edition, Dept, TotalBooks) VALUES (?, ?, ?, ?, ?)";
	        psmt = connection.prepareStatement(insertSql);
	        psmt.setString(1, book.getName());
	        psmt.setString(2, book.getAuthor());
	        psmt.setString(3, book.getEdition());
	        psmt.setString(4, book.getDept());
	        psmt.setInt(5, book.getNoOfBooks());
	        psmt.executeUpdate();
	        return "New Book Added Successfully.";
	    } else {
	        // Book exists, so we update the quantity
	        String updateSql = "UPDATE Books SET TotalBooks = TotalBooks + ? WHERE Name = ? AND Author = ?";
	        psmt = connection.prepareStatement(updateSql);
	        psmt.setInt(1, book.getNoOfBooks());
	        psmt.setString(2, book.getName());
	        psmt.setString(3, book.getAuthor());
	        psmt.executeUpdate();
	        return "Book Already Exists. Quantity Updated.";
	    }
	}

	@Override
	public String updateBookQuantity(int bookId, int quantity) throws ClassNotFoundException, SQLException {
		 connection = ConnectionHelper.getConnection();
	        
	        // SQL query to update the book quantity
	        String sql = "UPDATE Books SET TotalBooks = TotalBooks + ? WHERE Id = ?";
	        psmt = connection.prepareStatement(sql);
	        psmt.setInt(1, quantity);  // Set the new quantity
	        psmt.setInt(2, bookId);    // Set the bookId for which the quantity needs to be updated
	        
	        int rowsUpdated = psmt.executeUpdate();
	        
	        if (rowsUpdated > 0) {
	            return "Book quantity updated successfully.";
	        } else {
	            return "Error: No such book found with the provided bookId.";
	        }
	    
	}


	
}