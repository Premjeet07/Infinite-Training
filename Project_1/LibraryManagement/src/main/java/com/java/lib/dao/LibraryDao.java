package com.java.lib.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.lib.model.AdminLogin;
import com.java.lib.model.Books;
import com.java.lib.model.LibUsers;
import com.java.lib.model.TranBook;
import com.java.lib.model.TranReturn;

public interface LibraryDao {
	String createUser(LibUsers libUsers) throws ClassNotFoundException, SQLException;
	int login(LibUsers libUsers) throws ClassNotFoundException, SQLException;
	List<Books> searchBooks(String searchType,String searchValue) throws ClassNotFoundException, SQLException;
	String issueBook(String userName,int bookId)throws ClassNotFoundException, SQLException;
	List<TranBook> accountDetails(String userName) throws ClassNotFoundException, SQLException;
	String returnBook(String userName,int bookId)throws ClassNotFoundException, SQLException;
	List<TranReturn> returnHistory() throws ClassNotFoundException, SQLException;
	String CreateAdmin(AdminLogin adminLogin) throws ClassNotFoundException, SQLException;
	int AdminSignup(AdminLogin adminLogin ) throws ClassNotFoundException, SQLException;
	String addOrUpdateBook(Books book) throws ClassNotFoundException, SQLException;
    String updateBookQuantity(int bookId, int quantity) throws ClassNotFoundException, SQLException;




}
