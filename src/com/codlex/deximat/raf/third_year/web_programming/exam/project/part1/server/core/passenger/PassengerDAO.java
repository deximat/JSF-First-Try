package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.passenger;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class PassengerDAO {

	private static final PassengerDAO INSTANCE = new PassengerDAO();

	public static PassengerDAO get() {
		return INSTANCE;
	}

	// key - JMBG
	private Map<String, Passenger> registredPassengers = new TreeMap<String, Passenger>();

	public synchronized Collection<Passenger> getAllPassengers() {
		return this.registredPassengers.values();
	}

	public synchronized Passenger getPassengerByJMBG(final String JMBG) {
		return this.registredPassengers.get(JMBG);
	}

	public synchronized Passenger insertOrAttach(final Passenger passenger) {
		Passenger existingPassenger = this.registredPassengers.get(passenger
				.getJMBG());
		if (existingPassenger != null) {
			return existingPassenger;
		}
		this.registredPassengers.put(passenger.getJMBG(), passenger);
		return passenger;
	}

}
