package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private static final String ADDRESS = "localhost";

	private static final int PORT = 1234;

	private Socket server;

	private ObjectInputStream serverIn;

	private PrintWriter serverOut;

	public Client() {
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

	public synchronized Object getResult() {
		try {
			return this.serverIn.readObject();
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
}
