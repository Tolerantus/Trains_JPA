package com.entities;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Direction
 *
 */
@Entity

public class Direction implements Serializable {
@Id
@GeneratedValue (strategy=GenerationType.AUTO)
private int direction_id;
public Direction(int direction_id, int st_dep, int st_arr, long time,
		double cost) {
	super();
	this.direction_id = direction_id;
	this.st_dep = st_dep;
	this.st_arr = st_arr;
	this.time = time;
	this.cost = cost;
}

private int st_dep;
private int st_arr;
private long time;
private double cost;
	
public int getDirection_id() {
	return direction_id;
}

public void setDirection_id(int direction_id) {
	this.direction_id = direction_id;
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

public long getTime() {
	return time;
}

public void setTime(long time) {
	this.time = time;
}

public double getCost() {
	return cost;
}

public void setCost(double cost) {
	this.cost = cost;
}

	private static final long serialVersionUID = 1L;

public Direction() {
	super();
}
   
}
