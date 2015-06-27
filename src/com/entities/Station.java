package com.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Station
 *
 */
@Entity

public class Station implements Serializable {
@Id
@GeneratedValue (strategy=GenerationType.AUTO)
private int station_id;
private String station_name;	
	private static final long serialVersionUID = 1L;

	public Station(int station_id, String station_name) {
		super();
		this.station_id = station_id;
		this.station_name = station_name;
	}

	public Station() {
		super();
	}

	public int getStation_id() {
		return station_id;
	}

	public void setStation_id(int station_id) {
		this.station_id = station_id;
	}

	public String getStation_name() {
		return station_name;
	}

	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}
   
}
