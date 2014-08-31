package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.passenger.Passenger;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.passenger.PassengerDAO;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.AddReservationResponse;

public class Flight implements Serializable {

	private static final long serialVersionUID = 4181421455147017958L;
	private static final long CANCEL_SAFETY = 24; // h
	private Date arrivalTime;
	private Date departureTime;
	private String destination;
	private String flightId;
	private int hashCode;
	private int numberOfPassingers;

	private int numberOfReservations;
	private Map<String, Passenger> passengers = new HashMap<>();
	private String source;

	public Flight() {

	}

	public Flight(final String flightId, final Date departureTime,
			final Date arrivalTime, final String source,
			final String destination, final int numberOfPassingers) {
		this.flightId = flightId;
		this.hashCode = flightId.hashCode();
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.source = source;
		this.destination = destination;
		this.numberOfPassingers = numberOfPassingers;
	}

	public boolean cancel(final Passenger passenger) {
		if (this.passengers.get(passenger.getJMBG()) != null) {
			// it exists so
			this.passengers.remove(passenger.getJMBG());
			passenger.getFlights().remove(this);

			this.numberOfReservations--;
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(final Object obj) {
		if (!(obj instanceof Flight)) {
			return false;
		}
		Flight f = (Flight) obj;
		return this.hashCode == f.hashCode;
	}

	public Date getArrivalTime() {
		return this.arrivalTime;
	}

	public Date getDepartureTime() {
		return this.departureTime;
	}

	public String getDestination() {
		return this.destination;
	}

	public String getFlightId() {
		return this.flightId;
	}

	public int getNumberOfPassingers() {
		return this.numberOfPassingers;
	}

	public int getNumberOfReservations() {
		return this.numberOfReservations;
	}

	public Map<String, Passenger> getPassengers() {
		return this.passengers;
	}

	public List<Passenger> getPassengersList() {
		return new ArrayList<Passenger>(this.passengers.values());
	}

	public String getSource() {
		return this.source;
	}

	@Override
	public int hashCode() {
		return this.hashCode;
	}

	public AddReservationResponse reserve(final Passenger passenger) {
		int newNumberOfReservations = this.numberOfReservations + 1;
		if (newNumberOfReservations <= this.numberOfPassingers) {
			// success
			this.numberOfReservations = newNumberOfReservations;
			Passenger persistedPassenger = PassengerDAO.get().insertOrAttach(
					passenger);
			if (this.passengers.containsKey(passenger.getJMBG())) {
				return AddReservationResponse.ALREADY_RESERVED;
			}
			
			this.passengers.put(passenger.getJMBG(), persistedPassenger);
			persistedPassenger.addFlight(this);
			
			return AddReservationResponse.SUCCESS;
		} else {
			return AddReservationResponse.FULL;
		}
	}

	public void setArrivalTime(final Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setDepartureTime(final Date departureTime) {
		this.departureTime = departureTime;
	}

	public void setDestination(final String destination) {
		this.destination = destination;
	}

	public void setFlightId(final String flightId) {
		this.flightId = flightId;
		this.hashCode = flightId.hashCode();
	}

	public void setNumberOfPassingers(final int numberOfPassingers) {
		this.numberOfPassingers = numberOfPassingers;
	}

	public void setNumberOfReservations(final int numberOfReservations) {
		this.numberOfReservations = numberOfReservations;
	}

	public void setPassengers(final Map<String, Passenger> passengers) {
		this.passengers = passengers;
	}

	public void setSource(final String source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return this.flightId + ", od: " + this.source + ", do: "
				+ this.destination + ", od: " + this.departureTime + ",  do: "
				+ this.arrivalTime;
	}

	public boolean isCancelPossible() {
		// check for time constraint
		long now = System.currentTimeMillis();
		long millisUntilFlight = this.departureTime.getTime() - now;
		return TimeUnit.MILLISECONDS.toHours(millisUntilFlight) >= CANCEL_SAFETY;
	}
}
