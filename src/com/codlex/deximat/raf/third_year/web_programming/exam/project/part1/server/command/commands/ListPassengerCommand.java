package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.AbstractCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.passenger.Passenger;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.passenger.PassengerDAO;

public class ListPassengerCommand extends AbstractCommand {

	private String JMBG;

	public ListPassengerCommand(final String JMBG) {
		this.JMBG = JMBG;
	}

	@Override
	protected void executeCommand() {
		Passenger passenger = PassengerDAO.get().getPassengerByJMBG(this.JMBG);
		if (passenger != null) {
			this.result = passenger;
		} else {
			this.result = "There is no such passenger!";
		}
	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}

}
