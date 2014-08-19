package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.Worker;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.AbstractCommand;

public class Logoff extends AbstractCommand {

	@Override
	protected void executeCommand() {
		Worker.getCurrent().setUserInvoker(null);
		this.result = "Succesfully logged out.";
	}

	@Override
	public boolean shouldAutorize() {
		return true;
	}
}
