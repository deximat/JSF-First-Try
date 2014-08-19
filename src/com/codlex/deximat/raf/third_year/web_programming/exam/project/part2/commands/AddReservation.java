package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.CommandType;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.Client;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.AddReservationRequest;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.AddReservationResponse;

public class AddReservation {
	private Client client;

	public AddReservation(Client client) {
		this.client = client;
	}

	public AddReservationResponse execute(AddReservationRequest request) {
		this.client.sendCommandToServer(produceCommand(request));
		return (AddReservationResponse) this.client.getResult();
	}

	private String produceCommand(AddReservationRequest request) {
		return CommandType.RESERVE + " " + request.toString();
	}

}
