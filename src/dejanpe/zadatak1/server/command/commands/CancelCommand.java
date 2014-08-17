package dejanpe.zadatak1.server.command.commands;

import com.codlex.faces.first_application.messages.CancelReservationResponse;

import dejanpe.zadatak1.server.command.AbstractCommand;
import dejanpe.zadatak1.server.command.Command;
import dejanpe.zadatak1.server.core.flight.FlightDAO;
import dejanpe.zadatak1.server.core.passenger.Passenger;
import dejanpe.zadatak1.server.core.passenger.PassengerDAO;

public class CancelCommand extends AbstractCommand implements Command {

	private String flightId;
	private String JMBG;

	public CancelCommand(final String flightId, final String JMBG) {
		this.flightId = flightId;
		this.JMBG = JMBG;
	}

	@Override
	protected void executeCommand() {
		Passenger passenger = PassengerDAO.get().getPassengerByJMBG(this.JMBG);
		if (passenger == null) {
			this.result = CancelReservationResponse.PASSENGER_DOESNT_EXIST;
			return;
		}
		this.result = FlightDAO.get().cancel(this.flightId, passenger);
	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}

}
