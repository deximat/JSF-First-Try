package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.Command;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.CommandType;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.Client;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.CancelReservationResponse;

public class CancelReservation {
	private Client client;

	public CancelReservation(Client client) {
		this.client = client;
	}

	public CancelReservationResponse execute(String flightId,
			String passengerJMBG) {
		this.client
				.sendCommandToServer(produceCommand(flightId, passengerJMBG));
		return (CancelReservationResponse) this.client.getResult();
	}

	private String produceCommand(String flightId, String passengerJMBG) {
		return CommandType.CANCEL + Command.SEPARATOR + flightId + Command.SEPARATOR + passengerJMBG;
	}
}
