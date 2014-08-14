package dejanpe.zadatak1.server.command.commands;

import dejanpe.zadatak1.server.command.AbstractCommand;
import dejanpe.zadatak1.server.core.flight.Flight;
import dejanpe.zadatak1.server.core.flight.FlightDAO;

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
			this.result = null;
		}
	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}

}
