package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Train
 *
 */
@Entity

public class Train implements Serializable {
@Id 
@GeneratedValue(strategy=GenerationType.AUTO)
private int train_id;
private int train_seats;
	
	private static final long serialVersionUID = 1L;

	public Train() {
		super();
	}

	public Train(int train_id, int train_seats) {
		super();
		this.train_id = train_id;
		this.train_seats = train_seats;
	}

	public int getTrain_id() {
		return train_id;
	}

	public void setTrain_id(int train_id) {
		this.train_id = train_id;
	}

	public int getTrain_seats() {
		return train_seats;
	}

	public void setTrain_seats(int train_seats) {
		this.train_seats = train_seats;
	}
   
}
