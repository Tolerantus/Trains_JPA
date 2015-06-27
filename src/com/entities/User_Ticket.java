package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: User_Tickets
 *
 */
@Entity

public class User_Ticket implements Serializable {
@Id	
private int ticket_id;
private int user_id;
	
	private static final long serialVersionUID = 1L;

	public User_Ticket() {
		super();
	}

	public int getTicket_id() {
		return ticket_id;
	}

	public void setTicket_id(int ticket_id) {
		this.ticket_id = ticket_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
   
}
