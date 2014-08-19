package com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands.AddCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands.CancelCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands.ListAllCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands.ListCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands.ListPassengerCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands.LoginCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands.Logoff;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands.RegisterCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands.ReserveCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.command.commands.TopSecretCommand;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.Flight;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.passenger.Passenger;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.passenger.PassengerDAO;

public enum CommandType {

	ADD("ADD") {
		@Override
		public Command buildCommand(final String[] params) {
			// ADD <FLIGHT_ID> <DEPARTURE_DATE_TIME> <ARRIVAL_DATE_TIME>
			// <SOURCE> <DESTINATION> <NO_OF_PASSENGERS>
			if (params.length != 6) {
				return null;
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd-HH-mm");
			String flightId = params[0];
			Date departureTime;
			Date arrivalTime;
			try {
				departureTime = simpleDateFormat.parse(params[1]);
				arrivalTime = simpleDateFormat.parse(params[2]);
			} catch (ParseException e) {
				System.out.println("Parse exception!");
				return new AddCommand();
			}

			String source = params[3];
			String destination = params[4];
			int numberOfPassingers = Integer.parseInt(params[5]);
			return new AddCommand(new Flight(flightId, departureTime,
					arrivalTime, source, destination, numberOfPassingers));
		}
	},
	CANCEL("CANCEL") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 2) {
				return null;
			}
			String flightId = params[0];
			String JMBG = params[1];
			return new CancelCommand(flightId, JMBG);
		}
	},
	LIST("LIST") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 1) {
				return null;
			}
			String flightId = params[0];
			return new ListCommand(flightId);
		}
	},
	LIST_ALL("LIST_ALL") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 0) {
				return null;
			}
			return new ListAllCommand();
		}
	},
	LIST_PASSENGER("LIST_PASSENGER") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 1) {
				return null;
			}
			String passengerJMBG = params[0];
			return new ListPassengerCommand(passengerJMBG);
		}
	},
	LOGIN("LOGIN") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 1) {
				return null;
			}
			String username = params[0];
			return new LoginCommand(username);
		}
	},
	LOGOFF("LOGOFF") {

		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 0) {
				return null;
			}
			return new Logoff();
		}

	},
	REGISTER("REGISTER") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 1) {
				return null;
			}
			String username = params[0];
			return new RegisterCommand(username);
		}
	},
	RESERVE("RESERVE") {
		@Override
		public Command buildCommand(final String[] params) {
			if (params.length != 4) {
				return null;
			}
			String flightId = params[0];
			String JMBG = params[1];
			String name = params[2];
			String surname = params[3];
			// try to find passenger in cache
			Passenger passenger = PassengerDAO.get().insertOrAttach(
					new Passenger(JMBG, name, surname));
			return new ReserveCommand(flightId, passenger);
		}
	},
	TOP_SECRET("TOP_SECRET") {
		@Override
		public Command buildCommand(final String[] params) {
			return new TopSecretCommand();
		}
	};

	private final String id;

	private CommandType(final String id) {
		this.id = id;
	}

	public abstract Command buildCommand(String[] params);

	public String getIdentifier() {
		return this.id;
	}
}
