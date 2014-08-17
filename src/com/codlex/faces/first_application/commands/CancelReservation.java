package com.codlex.faces.first_application.commands;

import com.codlex.faces.first_application.Client;
import com.codlex.faces.first_application.messages.CancelReservationResponse;

import dejanpe.zadatak1.server.command.CommandType;

public class CancelReservation {
	private Client client;
	
	public CancelReservation(Client client) {
		this.client = client;
	}

	public CancelReservationResponse execute(String flightId, String passengerJMBG) { 
		this.client.sendCommandToServer(produceCommand(flightId, passengerJMBG));
		return  (CancelReservationResponse) this.client.getResult();
	}
	
	private String produceCommand(String flightId, String passengerJMBG) {
		return CommandType.CANCEL + " " + flightId + " " + passengerJMBG;
	}
}
