package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.Worker;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.AbstractCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.Command;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.user.User;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.user.UserDAO;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.LoginResponse;

public class LoginCommand extends AbstractCommand implements Command {

	private String username;

	public LoginCommand(final String username) {
		this.username = username;
	}

	@Override
	protected void executeCommand() {
		User userLoggedIn = UserDAO.get().findUserByUsername(this.username);
		if (userLoggedIn != null) {
			Worker.getCurrent().setUserInvoker(userLoggedIn);
			this.result = LoginResponse.SUCCESS;
		} else {
			this.result = LoginResponse.USER_NOT_FOUND;
		}
	}

	@Override
	public boolean shouldAutorize() {
		// this is autorization handler it will create user
		return false;
	}

}
