package com.java.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.bank.Accounts;
import com.java.bank.util.ConnectionHelper;

public class BankDaoImpl implements BankDao {
	Connection connection;
	PreparedStatement psmt;
	
	public int generateAccountNo() throws ClassNotFoundException, SQLException {
		String cmd="select case when max(accountNo)is null then 1 "
				+ "else max(accountNo)+1 end accno from accounts";
		connection=ConnectionHelper.getConnection();
		psmt=connection.prepareStatement(cmd);
		ResultSet rs = psmt.executeQuery();
		rs.next();
		return rs.getInt("accno");
	}

	@Override
	public String createAccount(Accounts accounts) throws ClassNotFoundException, SQLException {
		int id =generateAccountNo();
		accounts.setAccountNO(id);
		String cmd="Insert into Account (AccountNo,FirstName,Lastname,city,state,amount,cheqFacil,Accounttype)"
				+ "values(?,?,?,?,?,?,?,?)";
		connection=ConnectionHelper.getConnection();
		psmt=connection.prepareStatement(cmd);
		psmt.setInt(1, accounts.getAccountNO());
		psmt.setString(2, accounts.getFirstName());
		psmt.setString(3, accounts.getLastName());
		psmt.setString(4, accounts.getCity());
		psmt.setString(5, accounts.getState());
		psmt.setDouble(6, accounts.getAmount());
		psmt.setString(7, accounts.getCheqFacil());
		psmt.setString(8, accounts.getAccountType());
		return "Account created with acc no " +id;
				
	}

	@Override
	public Accounts searchAccount(int accountno) throws ClassNotFoundException, SQLException {
		Accounts accounts=null;
		String cmd="select * from accounts where accountno = ?";
		connection=ConnectionHelper.getConnection();
		psmt=connection.prepareStatement(cmd);
		psmt.setInt(1, accountno);
		ResultSet rs=psmt.executeQuery();
		if(rs.next()) {
			accounts =new Accounts();
			accounts.setAccountNO(rs.getInt("accountno"));
			accounts.setFirstName(rs.getString("firstname"));
			accounts.setLastName(rs.getString("lastname"));
			accounts.setCity(rs.getString("city"));
			accounts.setState(rs.getString("state"));
			accounts.setAmount(rs.getDouble("amount"));
			accounts.setCheqFacil(rs.getString("cheqfac"));
			accounts.setAccountType(rs.getString("accountType"));
			
		}
		
		return accounts;
	}

	@Override
	public String depositAccount(int accountno, double depositAmount) throws ClassNotFoundException, SQLException {
		connection=ConnectionHelper.getConnection();
		Accounts accounts=searchAccount(accountno);
		if(accounts!=null) {
			String cmd="update accounts set amount = amount + ? where accountno = ?";
			psmt=connection.prepareStatement(cmd);
			psmt.setDouble(1, depositAmount);
			psmt.setInt(2, accountno);
			psmt.executeUpdate();
			cmd="Insert into Trans(accountno,transamount,transtype) values(?,?,?)";
			psmt=connection.prepareStatement(cmd);
			psmt.setInt(1, accountno);
			psmt.setDouble(2, depositAmount);
			psmt.setString(3, "C");
			psmt.executeUpdate();
			return "amount credited";
		}
		return "account not found";
	}

	@Override
	public String withdrawalAccount(int accountno, double withdrawalAmount) throws ClassNotFoundException, SQLException {
		Accounts accounts= searchAccount(accountno);
		if(accounts!=null) {
			double balance=accounts.getAmount();
			if(balance-withdrawalAmount <0) {
				return "insuficient Funds";
			}
			String cmd="update accounts set amount = amount + ? where accountno = ?";
			psmt=connection.prepareStatement(cmd);
			psmt.setDouble(1, withdrawalAmount);
			psmt.setInt(2, accountno);
			psmt.executeUpdate();
			cmd="Insert into Trans(accountno,transamount,transtype) values(?,?,?)";
			psmt=connection.prepareStatement(cmd);
			psmt.setInt(1, accountno);
			psmt.setDouble(2, withdrawalAmount);
			psmt.setString(3, "D");
			psmt.executeUpdate();
			return "amount Debited";
			
		}
		return "account not found";
	}

}
