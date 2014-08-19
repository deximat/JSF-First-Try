package com.codlex.deximat.raf.third_year.web_programming.exam.project.part2.messages;

public enum CancelReservationResponse {
	FLIGHT_DOESNT_EXIST(
	"Reservation cancellation unssuccesful selected flight doesn't exist."), PASSENGER_DOESNT_EXIST(
					"Reservation cancellation unssuccesful selected passinger doesn't exist."), RESERVATION_NOT_POSSIBLE_TOO_LATE(
			"Reservations can't be canceled if flight is within next 24 hours."), SUCCESS(
			null), WRONG_FLIGHT(
			"Reservation cancellation unssuccesful selected passinger isn't on flight.");

	private String description;

	private CancelReservationResponse(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
}
