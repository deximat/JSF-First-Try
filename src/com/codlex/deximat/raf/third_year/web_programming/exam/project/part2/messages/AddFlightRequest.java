package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AddFlightRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String arrivalTime;
	private String departureTime;
	private String destination;
	private String flightId;
	private int numberOfPassingers;
	private String source;

	public String getArrivalTime() {
		return arrivalTime;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getDestination() {
		return destination;
	}

	public String getFlightId() {
		return flightId;
	}

	public int getNumberOfPassingers() {
		return numberOfPassingers;
	}

	public String getSource() {
		return source;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public void setNumberOfPassingers(int numberOfPassingers) {
		this.numberOfPassingers = numberOfPassingers;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return this.flightId + " " + this.departureTime + " "
				+ this.arrivalTime + " " + this.source + " " + this.destination
				+ " " + this.numberOfPassingers;
	}
}
