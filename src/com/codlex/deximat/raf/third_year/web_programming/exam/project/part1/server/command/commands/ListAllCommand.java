package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands;

import java.util.Collection;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.AbstractCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.Flight;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.FlightDAO;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.GetFlightsResponse;

public class ListAllCommand extends AbstractCommand {

	@Override
	protected void executeCommand() {
		Collection<Flight> flights = FlightDAO.get().getAllFlights();
		System.out.println("Found " + flights.size() + " flights!");
		this.result = new GetFlightsResponse(flights);
	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}

}
