package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.client.ConsoleShowable;

public enum CancelReservationResponse implements ConsoleShowable {
	FLIGHT_DOESNT_EXIST("Nepostojeci let."), PASSENGER_DOESNT_EXIST(
			"Nepostojeci putnik."), RESERVATION_NOT_POSSIBLE_TOO_LATE(
			"Rezervaciju je nemoguce otkazati ukoliko je let u narednih 24h."), SUCCESS(
			null), WRONG_FLIGHT("Dati putnik nema rezervaciju za dati let.");

	private String description;

	private CancelReservationResponse(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	@Override
	public String showInConsole() {
		return this.description;
	}
}
