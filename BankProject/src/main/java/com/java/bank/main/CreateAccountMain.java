package com.java.bank.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.bank.Accounts;
import com.java.bank.dao.BankDao;
import com.java.bank.dao.BankDaoImpl;

public class CreateAccountMain {
	public static void main(String[] args) {
		Accounts accounts =new Accounts();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter first name ");
		accounts.setFirstName(sc.next());
		System.out.println("enter your last name ");
		accounts.setLastName(sc.next());
		System.out.println("Enter city ");
		accounts.setCity(sc.next());
		System.out.println("enter state");
		accounts.setState(sc.next());
		System.out.println("Enter amount");
		accounts.setAmount(sc.nextDouble());
		System.out.println("Enter cheqefaclities(yes/no)");
		accounts.setCheqFacil(sc.next());
		System.out.println("Enter accountType ");
		accounts.setAccountType(sc.next());
		BankDao bankdao=new BankDaoImpl();
		try {
			System.out.println(bankdao.createAccount(accounts));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
