package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.CommandType;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.Client;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.GetFlightsResponse;

public class GetFlights {
	private Client client;

	public GetFlights(Client client) {
		this.client = client;
	}

	public GetFlightsResponse execute() {
		this.client.sendCommandToServer(produceCommand());
		return (GetFlightsResponse) this.client.getResult();
	}

	private String produceCommand() {
		return CommandType.LIST_ALL.getIdentifier();
	}
}
