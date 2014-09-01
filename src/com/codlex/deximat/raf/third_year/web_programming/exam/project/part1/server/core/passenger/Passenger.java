package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.passenger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.client.ConsoleShowable;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.Flight;

public class Passenger implements Serializable, ConsoleShowable {

	private static final long serialVersionUID = -4100224177712466654L;
	private List<Flight> flights = new ArrayList<>();
	private String JMBG;
	private String name;
	private String surname;

	public Passenger() {

	}

	public Passenger(final String JMBG, final String name, final String surname) {
		this.JMBG = JMBG;
		this.name = name;
		this.surname = surname;
	}

	public void addFlight(Flight flight) {
		this.flights.add(flight);
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public String getJMBG() {
		return this.JMBG;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
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

	@Override
	public String showInConsole() {
		return toString();
	}
	
	
}
