package com.codlex.faces.first_application;

import java.util.List;

import javax.el.ELContext;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.codlex.faces.first_application.beans.LoginData;
import com.codlex.faces.first_application.commands.AddFlight;
import com.codlex.faces.first_application.commands.AddReservation;
import com.codlex.faces.first_application.commands.GetFlights;
import com.codlex.faces.first_application.commands.ListFlight;
import com.codlex.faces.first_application.commands.ListPassenger;
import com.codlex.faces.first_application.commands.Login;
import com.codlex.faces.first_application.commands.Register;
import com.codlex.faces.first_application.messages.AddFlightRequest;
import com.codlex.faces.first_application.messages.AddReservationRequest;
import com.codlex.faces.first_application.messages.GetFlightsResponse;
import com.codlex.faces.first_application.messages.LoginResponse;
import com.codlex.faces.first_application.messages.RegisterResponse;

import dejanpe.zadatak1.server.core.flight.Flight;
import dejanpe.zadatak1.server.core.passenger.Passenger;

@SessionScoped
@ManagedBean
public class User {

	@ManagedProperty(value="#{loginData}")
	private LoginData loginData;
	
	
	@ManagedProperty(value="#{userMessageBox}")
	private UserMessageBox messageBox;

	
	public UserMessageBox getMessageBox() {
		return messageBox;
	}

	public void setMessageBox(UserMessageBox messageBox) {
		this.messageBox = messageBox;
	}

	private Client client;
	
	private ConfigurableNavigationHandler navigation 
			   = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();


	volatile private Flight currentFlight;


	private Passenger currentPassenger;
	
	public void requireLoggedIn() {
		boolean isLoggedIn = client != null;
		if (!isLoggedIn) {
			showMessage("Login to access this page!");
			this.navigation.performNavigation("ACCESS_DENIED");
		}
	}
	
	public void showMessage(String message) {
		this.messageBox.setCurrentMessage(message);
	}
	
	public LoginData getLoginData() {
		return loginData;
	}

	public void setLoginData(LoginData loginData) {
		this.loginData = loginData;
	}

	public String login() {
		Client client = new Client();
		LoginResponse response = new Login(client, this.loginData.getUsername()).execute();
		
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
	
	public String register() {
		Client client = new Client();
		RegisterResponse response = new Register(client, this.loginData.getUsername()).execute();
		
		switch (response) {
		case SUCCESS:
			showMessage("User " + this.loginData.getUsername() + " succesfully registred, you can now login!");
			break;
		case USER_ALREADY_EXISTS:
			showMessage("This username is taken!");
			break;
		default:
			System.out.println("Something is wrong!");
		}
		
		return response.toString();
	}
	
	public List<Flight> getFlights() {
		GetFlightsResponse response = new GetFlights(client).execute();
		return response.getFlights();
	}
	
	public Object getManagedBean(String name) {
		
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		return FacesContext.getCurrentInstance().getApplication()
		        .getELResolver().getValue(elContext, null, name);
	}
	
	public String addFlight() {
		AddFlightRequest request = (AddFlightRequest) getManagedBean("addFlightRequest");
		return new AddFlight(this.client).execute(request).toString();
	}
	
	public String logout() {
		this.client = null;
		return "SUCCESS";
	}
	
	public String showFlight(Flight flight) {
		this.currentFlight = new ListFlight(this.client, flight).execute();
		return "SUCCESS";
	}
	
	public Passenger getCurrentPassenger() {
		return this.currentPassenger;
	}
	
	public String showPassenger(Passenger passenger) {
		this.currentPassenger = new ListPassenger(this.client, passenger).execute();
		return "SUCCESS";
	}
	public synchronized Flight getCurrentFlight() {
		return this.currentFlight;
	}
	
	public String addReservation() {
		AddReservationRequest request = (AddReservationRequest) getManagedBean("addReservationRequest");
		request.setFlightId(this.currentFlight.getFlightId());
		return new AddReservation(this.client).execute(request).toString();
	}
}
