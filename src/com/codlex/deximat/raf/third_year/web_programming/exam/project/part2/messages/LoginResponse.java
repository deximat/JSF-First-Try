package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

import java.io.Serializable;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.client.ConsoleShowable;

public enum LoginResponse implements Serializable, ConsoleShowable {
	SUCCESS("Uspesno ste se ulogovali!"), USER_NOT_FOUND("Korisnik nije pronadjen!");

	private String description;

	
	LoginResponse(String description) {
		this.description = description;
	}
	
	@Override
	public String showInConsole() {
		return this.description;
	}
}
