package com.codlex.faces.first_application.messages;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AddFlightRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String flightId;
	private String departureTime;
	private String arrivalTime;
	private String source;
	private String destination;
	private int numberOfPassingers;
	
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public String getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getNumberOfPassingers() {
		return numberOfPassingers;
	}
	public void setNumberOfPassingers(int numberOfPassingers) {
		this.numberOfPassingers = numberOfPassingers;
	}
	
	@Override
	public String toString() {
		return this.flightId + " " + this.departureTime + " " + this.arrivalTime + " " + this.source + " " + this.destination + " " + this.numberOfPassingers;
	}
}
