package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.AbstractCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.FlightDAO;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.passenger.Passenger;

public class ReserveCommand extends AbstractCommand {

	private String flightId;
	private Passenger passenger;

	public ReserveCommand(final String flightId, final Passenger passenger) {
		this.flightId = flightId;
		this.passenger = passenger;
	}

	@Override
	protected void executeCommand() {
		this.result = FlightDAO.get().reserveFlight(this.flightId, this.passenger);
		System.out.println(this.result);
	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}

}
