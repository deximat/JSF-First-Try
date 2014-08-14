package dejanpe.zadatak1.server.command.commands;

import com.codlex.faces.first_application.messages.RegisterResponse;

import dejanpe.zadatak1.server.command.AbstractCommand;
import dejanpe.zadatak1.server.core.user.UserDAO;

public class RegisterCommand extends AbstractCommand {

	private final String username;

	public RegisterCommand(final String username) {
		this.username = username;
	}

	@Override
	public void executeCommand() {
		if (UserDAO.get().registerUser(this.username)) {
			this.result = RegisterResponse.SUCCESS;
		} else {
			this.result = RegisterResponse.USER_ALREADY_EXISTS;
		}
	}

	@Override
	public boolean shouldAutorize() {
		// no autorization is not needed for registration
		return false;
	}
}
