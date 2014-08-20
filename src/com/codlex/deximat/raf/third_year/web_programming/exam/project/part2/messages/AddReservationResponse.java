package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

public enum AddReservationResponse {
	FLIGHT_DOES_NOT_EXIST("Nepostojeci let!"), FULL("Na datom letu vise nema mesta!"), SUCCESS(null), ALREADY_RESERVED("Korisnik vec ima rezervaciju za dati let!");

	private String description;
	
	private AddReservationResponse(String description) {
		this.description = description;
	}
	public String getDescription() {
		return this.description;
	}
}
