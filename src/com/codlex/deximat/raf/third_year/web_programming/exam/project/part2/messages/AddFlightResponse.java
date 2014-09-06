package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.client.ConsoleShowable;

public enum AddFlightResponse implements ConsoleShowable {
	ALREADY_EXISTS("Let vec postoji!"), PARSE_FAILED("Parsiranje informacija o letu nije uspelo."), SUCCESS("Let uspesno dodat!");

	private String description;
	
	private AddFlightResponse(String description) {
		this.description = description;
	}
	
	
	public String getDescription() {
		return this.description;
	}

	@Override
	public String showInConsole() {
		return getDescription();
	}
}
