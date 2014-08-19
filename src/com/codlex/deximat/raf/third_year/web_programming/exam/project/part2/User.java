package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2;

import java.util.List;

import javax.el.ELContext;
import javax.faces.application.ConfigurableNavigationHandler;
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

	@ManagedProperty(value = "#{userMessageBox}")
	private UserMessageBox messageBox;

	private ConfigurableNavigationHandler navigation = (ConfigurableNavigationHandler) FacesContext
			.getCurrentInstance().getApplication().getNavigationHandler();

	public String addFlight() {
		AddFlightRequest request = (AddFlightRequest) getManagedBean("addFlightRequest");
		AddFlightResponse response = new AddFlight(this.client).execute(request);
		if (AddFlightResponse.SUCCESS.equals(response)) {
			this.currentFlight = request.toFlight();
		}
		showMessage(response.getDescription());
		return response.toString();
	}

	public String addReservation() {
		AddReservationRequest request = (AddReservationRequest) getManagedBean("addReservationRequest");
		request.setFlightId(this.currentFlight.getFlightId());
		return new AddReservation(this.client).execute(request).toString();
	}

	public String cancelCurrentReservation() {
		CancelReservationResponse response = new CancelReservation(this.client)
				.execute(this.currentFlight.getFlightId(),
						this.currentPassenger.getJMBG());
		showMessage(response.getDescription());
		return response.toString();
	}

	public String cancelFlight(Flight flight) {
		this.currentFlight = flight;
		return "SUCCESS";
	}

	public String cancelPassenger(Passenger passenger) {
		this.currentPassenger = passenger;
		return "SUCCESS";

	}

	public synchronized Flight getCurrentFlight() {
		return this.currentFlight;
	}

	public Passenger getCurrentPassenger() {
		return this.currentPassenger;
	}

	public List<Flight> getFlights() {
		GetFlightsResponse response = new GetFlights(client).execute();
		return response.getFlights();
	}

	public LoginData getLoginData() {
		return loginData;
	}

	public static Object getManagedBean(String name) {

		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		return FacesContext.getCurrentInstance().getApplication()
				.getELResolver().getValue(elContext, null, name);
	}

	public UserMessageBox getMessageBox() {
		return messageBox;
	}

	public String login() {
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

	public void logout() {
		this.client = null;
		showMessage("Uspesno ste se odjavili!");
		this.navigation.performNavigation("LOGOUT");
	}

	public String register() {
		Client client = new Client();
		RegisterResponse response = new Register(client,
				this.loginData.getUsername()).execute();

		switch (response) {
		case SUCCESS:
			showMessage("User " + this.loginData.getUsername()
					+ " succesfully registred, you can now login!");
			break;
		case USER_ALREADY_EXISTS:
			showMessage("This username is taken!");
			break;
		default:
			System.out.println("Something is wrong!");
		}

		return response.toString();
	}

	public void requireLoggedIn() {
		boolean isLoggedIn = client != null;
		if (!isLoggedIn) {
			showMessage("Login to access this page!");
			this.navigation.performNavigation("ACCESS_DENIED");
		}
	}

	public void setLoginData(LoginData loginData) {
		this.loginData = loginData;
	}

	public void setMessageBox(UserMessageBox messageBox) {
		this.messageBox = messageBox;
	}

	public String showFlight(Flight flight) {
		this.currentFlight = new ListFlight(this.client, flight).execute();
		return "SUCCESS";
	}

	public void showMessage(String message) {
		this.messageBox.setCurrentMessage(message);
	}

	public String showPassenger(Passenger passenger) {
		this.currentPassenger = new ListPassenger(this.client, passenger)
				.execute();
		return "SUCCESS";
	}

	public void afterPageLoad() {
		System.out.println("Pokrenuto");
		showMessage(null);
	}

}