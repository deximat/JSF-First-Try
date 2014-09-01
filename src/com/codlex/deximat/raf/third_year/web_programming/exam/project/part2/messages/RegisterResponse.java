package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

import java.io.Serializable;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.client.ConsoleShowable;

public enum RegisterResponse implements Serializable, ConsoleShowable {
	SUCCESS("Registracija uspesna!"), USER_ALREADY_EXISTS("Registracija nije uspesna, korisnik vec postoji!");
	private String description;

	
	RegisterResponse(String description) {
		this.description = description;
	}


	@Override
	public String showInConsole() {
		return this.description;
	}
	
	
}
