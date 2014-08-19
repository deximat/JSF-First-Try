package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.CommandType;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.Client;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.LoginResponse;


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
