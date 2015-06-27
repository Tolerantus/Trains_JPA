package com.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Seats
 *
 */
@Entity

public class Seats implements Serializable {
@Id
@GeneratedValue (strategy=GenerationType.AUTO)
private int seats_id;
private int journey_id;
private int route_step;
private int empty_seats;

public Seats(int seats_id, int journey_id, int route_step, int empty_seats) {
	super();
	this.seats_id = seats_id;
	this.journey_id = journey_id;
	this.route_step = route_step;
	this.empty_seats = empty_seats;
}

public int getSeats_id() {
	return seats_id;
}

public void setSeats_id(int seats_id) {
	this.seats_id = seats_id;
}

public int getJourney_id() {
	return journey_id;
}

public void setJourney_id(int journey_id) {
	this.journey_id = journey_id;
}

public int getRoute_step() {
	return route_step;
}

public void setRoute_step(int route_step) {
	this.route_step = route_step;
}

public int getEmpty_seats() {
	return empty_seats;
}

public void setEmpty_seats(int empty_seats) {
	this.empty_seats = empty_seats;
}

	private static final long serialVersionUID = 1L;

	public Seats() {
		super();
	}
   
}
