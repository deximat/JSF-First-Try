package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.Command;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.CommandType;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.Client;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.RegisterResponse;

public class Register {
	private Client client;
	private String username;

	public Register(Client client, String username) {
		this.client = client;
		this.username = username;
	}

	public RegisterResponse execute() {
		this.client.sendCommandToServer(produceCommand());
		return (RegisterResponse) this.client.getResult();
	}

	private String produceCommand() {
		return CommandType.REGISTER.getIdentifier() + Command.SEPARATOR + this.username;
	}
}
