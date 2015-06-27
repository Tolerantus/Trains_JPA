package com.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Route
 *
 */
@Entity

public class Route implements Serializable {
@Id
@GeneratedValue (strategy=GenerationType.AUTO)
private int route_id;
private String route_name;
	
	public int getRoute_id() {
	return route_id;
}

public Route(int route_id, String route_name) {
		super();
		this.route_id = route_id;
		this.route_name = route_name;
	}

public void setRoute_id(int route_id) {
	this.route_id = route_id;
}

public String getRoute_name() {
	return route_name;
}

public void setRoute_name(String route_name) {
	this.route_name = route_name;
}

	private static final long serialVersionUID = 1L;

	public Route() {
		super();
	}
   
}
