package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class AddReservationRequest {
	private String flightId;
	private String JMBG;
	private String name;
	private String surname;

	public String getFlightId() {
		return flightId;
	}

	public String getJMBG() {
		return JMBG;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return this.flightId + " " + this.JMBG + " " + this.name + " "
				+ this.surname;
	}

}
