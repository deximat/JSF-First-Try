package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.Flight;

public class GetFlightsResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<Flight> flights;
	
	public GetFlightsResponse(Collection<Flight> flights) {
		this.flights = new ArrayList<Flight>(flights);
	}

	public GetFlightsResponse() {
		
	}
	
	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
	
}
