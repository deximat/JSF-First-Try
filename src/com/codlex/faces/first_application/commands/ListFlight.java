package com.codlex.faces.first_application.commands;

import com.codlex.faces.first_application.Client;

import dejanpe.zadatak1.server.command.CommandType;
import dejanpe.zadatak1.server.core.flight.Flight;

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
