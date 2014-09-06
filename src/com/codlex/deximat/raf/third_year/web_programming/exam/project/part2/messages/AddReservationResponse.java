package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.client.ConsoleShowable;

public enum AddReservationResponse implements ConsoleShowable {
	FLIGHT_DOES_NOT_EXIST("Nepostojeci let!"), FULL("Na datom letu vise nema mesta!"), SUCCESS("Rezervacija uspesna!"), ALREADY_RESERVED("Korisnik vec ima rezervaciju za dati let!");

	private String description;
	
	private AddReservationResponse(String description) {
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
