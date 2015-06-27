package com.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Passenger
 *
 */
@Entity

public class Passenger implements Serializable {
@Id
@GeneratedValue (strategy=GenerationType.AUTO)
private int passenger_id;
public Passenger(int passenger_id, String passenger_name) {
	super();
	this.passenger_id = passenger_id;
	this.passenger_name = passenger_name;
}

private String passenger_name;
	private static final long serialVersionUID = 1L;

	public Passenger() {
		super();
	}

	public int getPassenger_id() {
		return passenger_id;
	}

	public void setPassenger_id(int passenger_id) {
		this.passenger_id = passenger_id;
	}

	public String getPassenger_name() {
		return passenger_name;
	}

	public void setPassenger_name(String passenger_name) {
		this.passenger_name = passenger_name;
	}
   
}
