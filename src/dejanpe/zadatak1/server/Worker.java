package dejanpe.zadatak1.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

import dejanpe.zadatak1.server.command.Command;
import dejanpe.zadatak1.server.command.CommandBuilder;
import dejanpe.zadatak1.server.core.user.User;

public class Worker extends Thread {
	private static final String END = "END_OF_STREAM_FF44FF";

	private final static ThreadLocal<Worker> CURRENT_WORKER = new ThreadLocal<Worker>();

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
		String stringCommand = this.in.nextLine();
		System.out.println("Command arrived: " + stringCommand);
		String[] commandAndParams = stringCommand.split(" ");
		String command = commandAndParams[0];
		String[] params = Arrays.copyOfRange(commandAndParams, 1, commandAndParams.length);
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
				// send response to client
				sendResponse(command.getResult());

				if (command.getResult().equals(END)) {
					break;
				}
			} else {
				sendResponse("Wrong number of parameters or command doesn't exist!");
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
