package com.codlex.faces.first_application.commands;

import com.codlex.faces.first_application.Client;
import com.codlex.faces.first_application.messages.GetFlightsResponse;
import dejanpe.zadatak1.server.command.CommandType;

public class GetFlights {
	private Client client;
	
	public GetFlights(Client client) {
		this.client = client;
	}
	
	public GetFlightsResponse execute() { 
		this.client.sendCommandToServer(produceCommand());
		return  (GetFlightsResponse) this.client.getResult();
	}
	
	private String produceCommand() {
		return CommandType.LIST_ALL.getIdentifier();
	}
}
