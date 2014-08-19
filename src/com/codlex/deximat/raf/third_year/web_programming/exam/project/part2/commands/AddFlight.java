package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.CommandType;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.Client;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.AddFlightRequest;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.AddFlightResponse;

public class AddFlight {
	private Client client;

	public AddFlight(Client client) {
		this.client = client;
	}

	public AddFlightResponse execute(AddFlightRequest request) {
		this.client.sendCommandToServer(produceCommand(request));
		return (AddFlightResponse) this.client.getResult();
	}

	private String produceCommand(AddFlightRequest request) {
		return CommandType.ADD + " " + request.toString();
	}
}
