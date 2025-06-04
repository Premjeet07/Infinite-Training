package com.java.bank.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.bank.Accounts;
import com.java.bank.dao.BankDao;
import com.java.bank.dao.BankDaoImpl;

public class SearchAccountMain {
	public static void main(String[] args) {
		int accountNo;
		Scanner sc = new Scanner(System.in);
		System.out.println("enter account no");
		accountNo=sc.nextInt();
		BankDao dao=new BankDaoImpl();
		try {
			Accounts accounts=dao.searchAccount(accountNo);
			if(accounts!=null) {
				System.out.println(accounts);
			}else {
				System.out.println("****account not found ***");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
