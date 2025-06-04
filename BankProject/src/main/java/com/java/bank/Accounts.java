package com.java.bank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Accounts {
	private int accountNO;
	private String firstName;
	private String lastName;
	private String city;;
	private String state;
	private double amount;
	private String cheqFacil;
	private String accountType;
	

}
