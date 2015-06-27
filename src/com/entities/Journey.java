package com.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Journey
 *
 */
@Entity

public class Journey implements Serializable {
@Id
@GeneratedValue (strategy=GenerationType.AUTO)
private int journey_id;
private int route_id;
private int train_id;
@Temporal(TemporalType.TIMESTAMP)
private Date time_dep;
	
	public int getJourney_id() {
	return journey_id;
}

public Journey(int journey_id, int route_id, int train_id, Date time_dep) {
		super();
		this.journey_id = journey_id;
		this.route_id = route_id;
		this.train_id = train_id;
		this.time_dep = time_dep;
	}

public void setJourney_id(int journey_id) {
	this.journey_id = journey_id;
}

public int getRoute_id() {
	return route_id;
}

public void setRoute_id(int route_id) {
	this.route_id = route_id;
}

public int getTrain_id() {
	return train_id;
}

public void setTrain_id(int train_id) {
	this.train_id = train_id;
}

public Date getTime_dep() {
	return time_dep;
}

public void setTime_dep(Date time_dep) {
	this.time_dep = time_dep;
}

	private static final long serialVersionUID = 1L;

	public Journey() {
		super();
	}
   
}
