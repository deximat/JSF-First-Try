package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.Command;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.CommandBuilder;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.user.User;

public class Worker extends Thread {
	private final static ThreadLocal<Worker> CURRENT_WORKER = new ThreadLocal<Worker>();

	private static final String END = "END_OF_STREAM_FF44FF";

	public static Worker getCurrent() {
		return CURRENT_WORKER.get();
	}

	private final Socket client;
	private Scanner in;

	private ObjectOutputStream out;

	private User userInvoker;

	public Worker(final Socket client) {
		this.client = client;
		try {
			this.in = new Scanner(client.getInputStream());
			this.out = new ObjectOutputStream(client.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	private Command readCommand() {
		String stringCommand = null;
		try {
			stringCommand = this.in.nextLine();
		} catch (NoSuchElementException e) {
			System.out.println("Client finished work!");
			return new Command() {
				@Override
				public void setUserInvoker(User userInvoker) {					
				}
				
				@Override
				public Object getResult() {
					return END;
				}
				
				@Override
				public void execute() {					
				}
			};
		}
		System.out.println("Command arrived: " + stringCommand);
		String[] commandAndParams = stringCommand.split(Command.SEPARATOR);
		String command = commandAndParams[0];
		String[] params = Arrays.copyOfRange(commandAndParams, 1,
				commandAndParams.length);
		Command commandBuilt = CommandBuilder.build(command, params);
		// if logged in this will enable us to execute autorization commands
		if (commandBuilt != null) {
			commandBuilt.setUserInvoker(this.userInvoker);
		}
		return commandBuilt;
	}

	@Override
	public void run() {
		CURRENT_WORKER.set(this);
		while (true) {
			// get command
			Command command = readCommand();
			if (command != null) {
				
				// execute command
				command.execute();
				
				// kill
				if (command.getResult() != null && command.getResult().equals(END)) {
					break;
				}
				
				// send response to client
				sendResponse(command.getResult());
				
			} else {
				sendResponse("Pogresan broj parametara ili komanda ne postoji!");
			}
		}
		try {
			this.client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void sendResponse(final Object toSend) {
		try {
			this.out.reset();
			this.out.writeObject(toSend);
			this.out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setUserInvoker(final User userInvoker) {
		this.userInvoker = userInvoker;
	}
}
