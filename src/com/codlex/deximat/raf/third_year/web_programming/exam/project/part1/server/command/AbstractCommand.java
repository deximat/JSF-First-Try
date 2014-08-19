package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.user.User;

public abstract class AbstractCommand implements Command {

	protected Object result;

	private User userInvoker;

	@Override
	public void execute() {
		if (!shouldAutorize() || this.userInvoker != null) {
			System.out.println("Started command " + getClass().getSimpleName());
			executeCommand();
			System.out
					.println("Finished command " + getClass().getSimpleName());
		} else {
			this.result = "You must be logged in to run command: "
					+ getClass().getSimpleName();
		}
	}

	protected abstract void executeCommand();

	@Override
	public Object getResult() {
		return this.result;
	}

	@Override
	public void setUserInvoker(final User userInvoker) {
		this.userInvoker = userInvoker;
	}

	public abstract boolean shouldAutorize();
}
