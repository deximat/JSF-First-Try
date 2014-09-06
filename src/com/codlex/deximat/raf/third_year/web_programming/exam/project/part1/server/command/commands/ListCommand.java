package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.AbstractCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.Flight;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.FlightDAO;

public class ListCommand extends AbstractCommand {

	private String flightId;

	public ListCommand(final String flightId) {
		this.flightId = flightId;
	}

	@Override
	protected void executeCommand() {
		Flight flight = FlightDAO.get().getFlightById(this.flightId);
		if (flight != null) {
			System.out.println("Produced " + flight);
			this.result = flight;
		} else {
			this.result = "Let sa datim ID-em nije pronadjen!";
		}
	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}

}
