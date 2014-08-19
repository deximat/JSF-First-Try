package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.CommandType;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.passenger.Passenger;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.Client;

public class ListPassenger {
	private Client client;
	private Passenger passenger;

	public ListPassenger(Client client, Passenger passenger) {
		this.client = client;
		this.passenger = passenger;
	}

	public Passenger execute() {
		this.client.sendCommandToServer(produceCommand());
		Passenger passenger = (Passenger) this.client.getResult();
		System.out.println("Arrived: " + passenger);
		return passenger;
	}

	private String produceCommand() {
		return CommandType.LIST_PASSENGER + " " + this.passenger.getJMBG();
	}
}
