package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

public enum AddFlightResponse {
	ALREADY_EXISTS("Let vec postoji!"), PARSE_FAILED("Parsiranje informacija o letu nije uspelo."), SUCCESS(null);

	private String description;
	
	private AddFlightResponse(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
