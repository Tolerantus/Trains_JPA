package com.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {
@Id	
@GeneratedValue (strategy=GenerationType.AUTO)
private int user_id;
private String user_login;
private String user_password;
private boolean account_type;

public User(int user_id, String user_login, String user_password,
		boolean account_type) {
	super();
	this.user_id = user_id;
	this.user_login = user_login;
	this.user_password = user_password;
	this.account_type = account_type;
}


public int getUser_id() {
	return user_id;
}

public void setUser_id(int user_id) {
	this.user_id = user_id;
}

public String getUser_login() {
	return user_login;
}

public void setUser_login(String user_login) {
	this.user_login = user_login;
}

public String getUser_password() {
	return user_password;
}

public void setUser_password(String user_password) {
	this.user_password = user_password;
}

public boolean getAccount_type() {
	return account_type;
}

public void setAccount_type(boolean account_type) {
	this.account_type = account_type;
}



	private static final long serialVersionUID = 1L;

	public User() {
		super();
	}
   
}
