package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.CommandType;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.Flight;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.Client;

public class ListFlight {
	private Client client;
	private Flight flight;
	
	public ListFlight(Client client, Flight flight) {
		this.client = client;
		this.flight = flight;
	}
	
	public Flight execute() { 
		this.client.sendCommandToServer(produceCommand());
		Flight flight = (Flight) this.client.getResult();
		System.out.println("Arrived: " + flight);
		return  flight;
	}
	
	private String produceCommand() {
		return CommandType.LIST + " " + this.flight.getFlightId();
	}
}
