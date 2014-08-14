package dejanpe.zadatak1.server.core.flight;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import com.codlex.faces.first_application.messages.AddReservationResponse;

import dejanpe.zadatak1.server.core.passenger.Passenger;

public class FlightDAO {

	private static final String FLIGHTS_PERSISTANCE_FILE = "flights.txt";

	private static final FlightDAO INSTANCE = new FlightDAO();

	public static FlightDAO get() {
		return INSTANCE;
	}

	private Map<String, Flight> registredFlights = new TreeMap<String, Flight>();

	private FlightDAO() {
		this.registredFlights = loadFlights();
	}

	public synchronized String cancel(final String flightId, final Passenger passenger) {
		Flight flight = getFlightById(flightId);
		if (flight == null) {
			return "Flight doesn't exist";
		}
		// check for time constraint
		long now = System.currentTimeMillis();
		long millisUntilFlight = flight.getDepartureTime().getTime() - now;

		if (TimeUnit.MILLISECONDS.toHours(millisUntilFlight) < 24) {
			return "It is too late to cancel reservation now!";
		}

		if (flight.cancel(passenger)) {
			persist();
			return "Canceled reservation succesfully";
		} else {
			return "Passinger is not on this flight!";
		}
	}

	public synchronized Collection<Flight> getAllFlights() {
		return this.registredFlights.values();
	}

	public synchronized Flight getFlightById(final String flightId) {
		return this.registredFlights.get(flightId);
	}

	public synchronized boolean insert(final Flight flight) {
		if (this.registredFlights.get(flight.getFlightId()) != null) {
			return false;
		}
		this.registredFlights.put(flight.getFlightId(), flight);
		persist();
		return true;
	}

	@SuppressWarnings("unchecked")
	public TreeMap<String, Flight> loadFlights() {
		XMLDecoder decoder = null;
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FLIGHTS_PERSISTANCE_FILE)));
			return (TreeMap<String, Flight>) decoder.readObject();
		} catch (FileNotFoundException e) {
			System.out.println("No flights to load!");
			return new TreeMap<String, Flight>();
		}  finally {
			if (decoder != null) {
				decoder.close();
			}
		}
	}

	private void persist() {
		try {
			XMLEncoder encoder = new XMLEncoder(
					new BufferedOutputStream(new FileOutputStream(FLIGHTS_PERSISTANCE_FILE)));
			encoder.writeObject(this.registredFlights);
			encoder.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public synchronized AddReservationResponse reserveFlight(final String flightId, final Passenger passenger) {
		Flight flight = this.registredFlights.get(flightId);
		if (flight == null) {
			return AddReservationResponse.FLIGHT_DOES_NOT_EXIST;
		}
		// now that we have valid flight try to reserve on flight
		if (flight.reserve(passenger)) {
			persist();
			return AddReservationResponse.SUCCESS;
		} else {
			return AddReservationResponse.FULL;
		}
	}

}
