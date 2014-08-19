package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.AbstractCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.Flight;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.FlightDAO;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.AddFlightResponse;

public class AddCommand extends AbstractCommand {

	private Flight flight;

	public AddCommand(final Flight flight) {
		this.flight = flight;
	}

	/**
	 * Instantiates for failed parsing. 
	 */
	public AddCommand() {
		this.result = AddFlightResponse.PARSE_FAILED;
	}

	@Override
	protected void executeCommand() {
		if (FlightDAO.get().insert(this.flight)) {
			this.result = AddFlightResponse.SUCCESS;
		} else {
			this.result = AddFlightResponse.ALREADY_EXISTS;
		}

	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}

}
