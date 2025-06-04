package com.java.lib.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data

public class Books {
	private int id;
	private String name;
	private String author;
	private String edition;
	private String dept;
	private int noOfBooks;

}
