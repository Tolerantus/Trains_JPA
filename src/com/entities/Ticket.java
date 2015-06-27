package com.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ticket
 *
 */
@Entity

public class Ticket implements Serializable {
@Id
@GeneratedValue (strategy=GenerationType.AUTO)
private int ticket_id;
private int passenger_id;
private int journey_id;
private int st_dep;
private int st_arr;
	

public Ticket(int ticket_id, int passenger_id, int journey_id, int st_dep,
		int st_arr) {
	super();
	this.ticket_id = ticket_id;
	this.passenger_id = passenger_id;
	this.journey_id = journey_id;
	this.st_dep = st_dep;
	this.st_arr = st_arr;
}

public int getTicket_id() {
	return ticket_id;
}

public void setTicket_id(int ticket_id) {
	this.ticket_id = ticket_id;
}

public int getPassenger_id() {
	return passenger_id;
}

public void setPassenger_id(int passenger_id) {
	this.passenger_id = passenger_id;
}

public int getJourney_id() {
	return journey_id;
}

public void setJourney_id(int journey_id) {
	this.journey_id = journey_id;
}

public int getSt_dep() {
	return st_dep;
}

public void setSt_dep(int st_dep) {
	this.st_dep = st_dep;
}

public int getSt_arr() {
	return st_arr;
}

public void setSt_arr(int st_arr) {
	this.st_arr = st_arr;
}

	private static final long serialVersionUID = 1L;

	public Ticket() {
		super();
	}
   
}
