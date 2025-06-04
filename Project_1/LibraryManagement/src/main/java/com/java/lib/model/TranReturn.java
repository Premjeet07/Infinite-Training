package com.java.lib.model;

import java.sql.Date;

import lombok.Data;

@Data

public class TranReturn {
	private String username;
	private int bookId;
	private Date fromDate;
	private Date ToDate;


}
