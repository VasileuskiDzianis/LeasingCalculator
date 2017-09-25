package by.vasilevsky.leasing.controller.command;

import by.vasilevsky.leasing.controller.command.implementation.*;

public class CommandProviderImpl implements CommandProvider {
	private static volatile CommandProviderImpl instance;

	private CommandProviderImpl() {

	}

	public static CommandProvider getInstance() {
		CommandProviderImpl localInstance = instance;
		if (localInstance == null) {
			synchronized (CommandProviderImpl.class) {
				localInstance = instance;
				if (localInstance == null) {
					instance = localInstance = new CommandProviderImpl();
				}
			}
		}
		return localInstance;
	}

	@Override
	public Command getCommand(final String urlMapping) {
		String lowerCaseUrl = urlMapping.toLowerCase();
		switch (lowerCaseUrl) {
		case "get:": {

			return HomeGetCommand.getInstance();
		}
		case "get:/home": {

			return HomeGetCommand.getInstance();
		}
		case "get:/calculate": {

			return CalculateGetCommand.getInstance();
		}
		case "post:/calculate": {

			return CalculatePostCommand.getInstance();
		}
		case "get:/logination": {

			return LoginationGetCommand.getInstance();
		}
		case "post:/logination": {

			return LoginationPostCommand.getInstance();
		}
		case "get:/logout": {

			return LogoutGetCommand.getInstance();
		}
		case "get:/profile": {

			return ProfileGetCommand.getInstance();
		}
		case "post:/profile": {

			return ProfilePostCommand.getInstance();
		}
		case "get:/registration": {

			return RegistrationGetCommand.getInstance();
		}
		case "post:/registration": {

			return RegistrationPostCommand.getInstance();
		}
		case "get:/users": {

			return UsersGetCommand.getInstance();
		}
		case "post:/users": {

			return UsersPostCommand.getInstance();
		}
		default: {

			return NotFound404Command.getInstance();
		}
		}
	}
}
