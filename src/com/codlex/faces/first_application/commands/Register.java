package com.codlex.faces.first_application.commands;

import com.codlex.faces.first_application.Client;
import com.codlex.faces.first_application.messages.RegisterResponse;

import dejanpe.zadatak1.server.command.CommandType;

public class Register {
	private Client client;
	private String username;
	
	public Register(Client client, String username) {
		this.client = client;
		this.username = username;
	}
	
	public RegisterResponse execute() { 
		this.client.sendCommandToServer(produceCommand());
		return  (RegisterResponse) this.client.getResult();
	}
	
	private String produceCommand() {
		return CommandType.REGISTER.getIdentifier() + " " + this.username;
	}
}
