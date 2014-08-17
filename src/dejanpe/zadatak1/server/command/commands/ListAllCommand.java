package dejanpe.zadatak1.server.command.commands;

import java.util.Collection;

import com.codlex.faces.first_application.messages.GetFlightsResponse;

import dejanpe.zadatak1.server.command.AbstractCommand;
import dejanpe.zadatak1.server.core.flight.Flight;
import dejanpe.zadatak1.server.core.flight.FlightDAO;

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
