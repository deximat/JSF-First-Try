package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

import java.io.Serializable;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.client.ConsoleShowable;

public enum LoginResponse implements Serializable, ConsoleShowable {
	SUCCESS, USER_NOT_FOUND;

	@Override
	public String showInConsole() {
		return name();
	}
}
