package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.Command;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.CommandType;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.Flight;

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
		return this.flightId + Command.SEPARATOR + this.departureTime + Command.SEPARATOR
				+ this.arrivalTime + Command.SEPARATOR + this.source + Command.SEPARATOR + this.destination
				+ Command.SEPARATOR + this.numberOfPassingers;
	}

	public Flight toFlight() {
		return new Flight(this.flightId,
				CommandType.parseDate(this.departureTime),
				CommandType.parseDate(this.arrivalTime), this.source,
				this.destination, this.numberOfPassingers);
	}
}
