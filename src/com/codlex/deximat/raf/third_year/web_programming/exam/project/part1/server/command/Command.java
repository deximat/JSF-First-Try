package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.user.User;

public interface Command {
	
	String SEPARATOR = "/";

	void execute();

	Object getResult();

	void setUserInvoker(User userInvoker);
}
