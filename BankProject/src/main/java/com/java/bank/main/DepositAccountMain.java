package com.java.bank.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.bank.dao.BankDao;
import com.java.bank.dao.BankDaoImpl;

public class DepositAccountMain {
	public static void main(String[] args) {
		int accountno;
		double depositAmount;
		Scanner sc = new Scanner(System.in);
		System.out.println("enter account no");
		accountno=sc.nextInt();
		System.out.println("enter deposit amount ");
		depositAmount =sc.nextDouble();
		BankDao dao=new BankDaoImpl();
		try {
			System.out.println(dao.depositAccount(accountno, depositAmount));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
