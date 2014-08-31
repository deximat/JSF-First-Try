package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread {
	
	private static final Object EXIT_COMMAND = "QUIT";

	private Scanner consoleIn;

	public static void main(final String[] args) {
		new Client().start();
	}	
	
	private static final String ADDRESS = "localhost";

	private static final int PORT = 1234;

	private Socket server;

	private ObjectInputStream serverIn;

	private PrintWriter serverOut;

	public Client() {
		this.consoleIn = new Scanner(System.in);
		try {
			this.server = new Socket(ADDRESS, PORT);
			this.serverIn = new ObjectInputStream(this.server.getInputStream());
			this.serverOut = new PrintWriter(this.server.getOutputStream());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized ConsoleShowable getResult() {
		try {
			return (ConsoleShowable) this.serverIn.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public synchronized void sendCommandToServer(final String command) {
		this.serverOut.print(command + "\n");
		this.serverOut.flush();
	}

	@Override
	public void run() {

		System.out.println("Welcome to GALAXY system, please do your work!");
		while (true) {
			String command = this.consoleIn.nextLine();
			if (command.equals(EXIT_COMMAND)) {
				System.out.println("Thanks for using GALAXY system!");
				return;
			}
			sendCommandToServer(command);
			ConsoleShowable consoleShowable = getResult();
			System.out.println(consoleShowable == null ? null : getResult().showInConsole());
			System.out.println("What more, master?");
		}
	}
}
