package com.codlex.faces.first_application.commands;

import com.codlex.faces.first_application.Client;
import com.codlex.faces.first_application.messages.AddFlightRequest;
import com.codlex.faces.first_application.messages.AddFlightResponse;
import dejanpe.zadatak1.server.command.CommandType;

public class AddFlight {
	private Client client;
	
	public AddFlight(Client client) {
		this.client = client;
	}

	public AddFlightResponse execute(AddFlightRequest request) { 
		this.client.sendCommandToServer(produceCommand(request));
		return  (AddFlightResponse) this.client.getResult();
	}
	
	private String produceCommand(AddFlightRequest request) {
		return CommandType.ADD + " " + request.toString();
	}
}
