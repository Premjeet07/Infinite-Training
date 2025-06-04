package com.java.bank.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.bank.dao.BankDao;
import com.java.bank.dao.BankDaoImpl;

public class WithdrawalAccountMain {
	public static void main(String[] args) {
		int accountNo;
		double withdrwalAmount;
		Scanner sc = new Scanner(System.in);
		System.out.println("enter account no");
		accountNo =sc.nextInt();
		System.out.println("enter withdrwal amount");
		withdrwalAmount=sc.nextDouble();
		BankDao dao=new BankDaoImpl();
		try {
			System.out.println(dao.withdrawalAccount(accountNo, withdrwalAmount));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
