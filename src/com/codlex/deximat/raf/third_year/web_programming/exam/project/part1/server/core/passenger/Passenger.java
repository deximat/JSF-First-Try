package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.passenger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.Flight;

public class Passenger implements Serializable {

	private static final long serialVersionUID = -4100224177712466654L;
	private String JMBG;
	private String name;
	private String surname;
	private List<Flight> flights = new ArrayList<>();

	public Passenger() {

	}

	public Passenger(final String JMBG, final String name, final String surname) {
		this.JMBG = JMBG;
		this.name = name;
		this.surname = surname;
	}

	public String getJMBG() {
		return this.JMBG;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setJMBG(final String jMBG) {
		this.JMBG = jMBG;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setSurname(final String surname) {
		this.surname = surname;
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.surname;
	}

	public void addFlight(Flight flight) {
		this.flights.add(flight);
	}
}
