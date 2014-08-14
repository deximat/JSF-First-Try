package com.codlex.faces.first_application.commands;

import com.codlex.faces.first_application.Client;
import com.codlex.faces.first_application.messages.AddReservationRequest;
import com.codlex.faces.first_application.messages.AddReservationResponse;

import dejanpe.zadatak1.server.command.CommandType;

public class AddReservation {
private Client client;
	
	public AddReservation(Client client) {
		this.client = client;
	}

	public AddReservationResponse execute(AddReservationRequest request) { 
		this.client.sendCommandToServer(produceCommand(request));
		return  (AddReservationResponse) this.client.getResult();
	}
	
	private String produceCommand(AddReservationRequest request) {
		return CommandType.RESERVE + " " + request.toString();
	}

}
