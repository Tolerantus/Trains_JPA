package com.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Shedule
 *
 */
@Entity

public class Shedule implements Serializable {
@Id
@GeneratedValue (strategy=GenerationType.AUTO)
private int shedule_id;
private int direction_id;
private int route_id;
private int step;
	
	public Shedule(int shedule_id, int direction_id, int route_id, int step) {
	super();
	this.shedule_id = shedule_id;
	this.direction_id = direction_id;
	this.route_id = route_id;
	this.step = step;
}

	public int getShedule_id() {
	return shedule_id;
}

public void setShedule_id(int shedule_id) {
	this.shedule_id = shedule_id;
}

public int getDirection_id() {
	return direction_id;
}

public void setDirection_id(int direction_id) {
	this.direction_id = direction_id;
}

public int getRoute_id() {
	return route_id;
}

public void setRoute_id(int route_id) {
	this.route_id = route_id;
}

public int getStep() {
	return step;
}

public void setStep(int step) {
	this.step = step;
}

	private static final long serialVersionUID = 1L;

	public Shedule() {
		super();
	}
   
}
