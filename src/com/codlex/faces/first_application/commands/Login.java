package com.codlex.faces.first_application.commands;

import com.codlex.faces.first_application.Client;
import com.codlex.faces.first_application.messages.LoginResponse;

import dejanpe.zadatak1.server.command.CommandType;


public class Login {
	
	private Client client;
	private String username;
	
	public Login(Client client, String username) {
		this.client = client;
		this.username = username;
	}
	
	public LoginResponse execute() { 
		this.client.sendCommandToServer(produceCommand());
		return  (LoginResponse) this.client.getResult();
	}
	
	private String produceCommand() {
		return CommandType.LOGIN.getIdentifier() + " " + this.username;
	}
}
