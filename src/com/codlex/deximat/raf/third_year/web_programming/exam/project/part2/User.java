package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2;

import java.util.List;

import javax.el.ELContext;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.flight.Flight;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part1.server.core.passenger.Passenger;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.beans.LoginData;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands.AddFlight;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands.AddReservation;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands.CancelReservation;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands.GetFlights;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands.ListFlight;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands.ListPassenger;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands.Login;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.commands.Register;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.AddFlightRequest;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.AddFlightResponse;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.AddReservationRequest;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.AddReservationResponse;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.CancelReservationResponse;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.GetFlightsResponse;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.LoginResponse;
import com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages.RegisterResponse;

@SessionScoped
@ManagedBean
public class User {
	private Client client;

	volatile private Flight currentFlight;

	private Passenger currentPassenger;

	@ManagedProperty(value = "#{loginData}")
	private LoginData loginData;

	private ConfigurableNavigationHandler navigation = (ConfigurableNavigationHandler) FacesContext
			.getCurrentInstance().getApplication().getNavigationHandler();

	public synchronized String addFlight() {
		AddFlightRequest request = (AddFlightRequest) getManagedBean("addFlightRequest");
		AddFlightResponse response = new AddFlight(this.client)
				.execute(request);
		if (AddFlightResponse.SUCCESS.equals(response)) {
			this.currentFlight = request.toFlight();
		} else {
			showMessage(response.getDescription());
		}
		return response.toString();
	}

	public synchronized String addReservation() {
		AddReservationRequest request = (AddReservationRequest) getManagedBean("addReservationRequest");
		request.setFlightId(this.currentFlight.getFlightId());
		AddReservationResponse response = new AddReservation(this.client)
				.execute(request);
		
		if (AddReservationResponse.SUCCESS.equals(response)) {
			showPassenger(request.getJMBG());
		} else {
			showMessage(response.getDescription());
		}
		return response.toString();
	}

	public synchronized String cancelCurrentReservation() {
		CancelReservationResponse response = new CancelReservation(this.client)
				.execute(this.currentFlight.getFlightId(),
						this.currentPassenger.getJMBG());
		if (!CancelReservationResponse.SUCCESS.equals(response)) {
			showMessage(response.getDescription());
		}
		return response.toString();
	}

	public synchronized String cancelFlight(Flight flight) {
		this.currentFlight = flight;
		return "SUCCESS";
	}

	public synchronized String cancelPassenger(Passenger passenger) {
		this.currentPassenger = passenger;
		return "SUCCESS";

	}

	public synchronized Flight getCurrentFlight() {
		return this.currentFlight;
	}

	public synchronized Passenger getCurrentPassenger() {
		return this.currentPassenger;
	}

	public synchronized List<Flight> getFlights() {
		GetFlightsResponse response = new GetFlights(client).execute();
		return response.getFlights();
	}

	public synchronized LoginData getLoginData() {
		return loginData;
	}

	public static Object getManagedBean(String name) {

		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		return FacesContext.getCurrentInstance().getApplication()
				.getELResolver().getValue(elContext, null, name);
	}

	public synchronized String login() {
		Client client = new Client();
		LoginResponse response = new Login(client, this.loginData.getUsername())
				.execute();

		switch (response) {
		case SUCCESS:
			this.client = client;
			break;
		case USER_NOT_FOUND:
			showMessage("User is not found!");
			break;
		default:
			System.out.println("Something is wrong!");
		}

		return response.toString();
	}

	public synchronized void logout() {
		this.client = null;
		showMessage("Uspesno ste se odjavili!");
		this.navigation.performNavigation("LOGOUT");
	}

	public synchronized String register() {
		Client client = new Client();
		RegisterResponse response = new Register(client,
				this.loginData.getUsername()).execute();

		switch (response) {
		case SUCCESS:
			showMessage("User " + this.loginData.getUsername()
					+ " je uspesno registrovan, mozete se ulogovati!");
			break;
		case USER_ALREADY_EXISTS:
			showMessage("Korisnicko ime je zauzeto!");
			break;
		default:
			System.out.println("Nesto nije u redu!");
		}

		return response.toString();
	}

	public synchronized void requireLoggedIn() {
		boolean isLoggedIn = client != null;
		if (!isLoggedIn) {
			showMessage("Login to access this page!");
			this.navigation.performNavigation("ACCESS_DENIED");
		}
	}

	public synchronized void setLoginData(LoginData loginData) {
		this.loginData = loginData;
	}

	public synchronized String showFlight(Flight flight) {
		this.currentFlight = new ListFlight(this.client, flight).execute();
		return "SUCCESS";
	}

	public synchronized void showMessage(String message) {
		// I have only one place to show message
		if (message != null && !message.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("message",
					new FacesMessage(message));
		}
	}

	public synchronized String showPassenger(String passengerJMBG) {
		this.currentPassenger = new ListPassenger(this.client, passengerJMBG)
				.execute();
		return "SUCCESS";
	}

}
