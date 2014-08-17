package com.codlex.faces.first_application.commands;

import com.codlex.faces.first_application.Client;

import dejanpe.zadatak1.server.command.CommandType;
import dejanpe.zadatak1.server.core.passenger.Passenger;

public class ListPassenger {
	private Client client;
	private Passenger passenger;
	
	public ListPassenger(Client client, Passenger passenger) {
		this.client = client;
		this.passenger = passenger;
	}
	
	public Passenger execute() { 
		this.client.sendCommandToServer(produceCommand());
		Passenger passenger = (Passenger) this.client.getResult();
		System.out.println("Arrived: " + passenger);
		return  passenger;
	}
	
	private String produceCommand() {
		return CommandType.LIST_PASSENGER + " " + this.passenger.getJMBG();
	}
}
