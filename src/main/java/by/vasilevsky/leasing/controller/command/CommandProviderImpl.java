package by.vasilevsky.leasing.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.vasilevsky.leasing.controller.command.impl.*;

public final class CommandProviderImpl implements CommandProvider {
	private static volatile CommandProviderImpl instance;

	private static final String METHOD_GET = "get";
	private static final String METHOD_POST = "post";
	private static final String PATH_ROOT = "";
	private static final String PATH_HOME = "/home";
	private static final String PATH_CALCULATE = "/calculate";
	private static final String PATH_LOGINATION = "/logination";
	private static final String PATH_LOGOUT = "/logout";
	private static final String PATH_PROFILE = "/profile";
	private static final String PATH_REGISTRATION = "/registration";
	private static final String PATH_USERS = "/users";

	private static final Command NOT_FOUND_404_COMMAND = new NotFound404Command();

	private final Map<String, Map<String, Command>> commandMapping;

	private CommandProviderImpl() {
		commandMapping = new HashMap<String, Map<String, Command>>();
		Map<String, Command> methodGetMapping = new HashMap<String, Command>();
		methodGetMapping.put(PATH_ROOT, new HomePageCommand());
		methodGetMapping.put(PATH_HOME, methodGetMapping.get(PATH_ROOT));
		methodGetMapping.put(PATH_CALCULATE, new CalculatorFormCommand());
		methodGetMapping.put(PATH_LOGINATION, new LoginationFormCommand());
		methodGetMapping.put(PATH_LOGOUT, new LogoutCommand());
		methodGetMapping.put(PATH_PROFILE, new ProfileFormCommand());
		methodGetMapping.put(PATH_REGISTRATION, new RegistrationFormCommand());
		methodGetMapping.put(PATH_USERS, new UsersGetListCommand());

		Map<String, Command> methodPostMapping = new HashMap<String, Command>();
		methodPostMapping.put(PATH_CALCULATE, new CalculatePaymentsCommand());
		methodPostMapping.put(PATH_LOGINATION, new LoginationProceedCommand());
		methodPostMapping.put(PATH_PROFILE, new ProfileUpdateCommand());
		methodPostMapping.put(PATH_REGISTRATION, new RegistrationProceedCommand());
		methodPostMapping.put(PATH_USERS, new UsersDeleteCommand());

		commandMapping.put(METHOD_GET, methodGetMapping);
		commandMapping.put(METHOD_POST, methodPostMapping);
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
	public Command getCommand(final String method, String url) {
		Command command = commandMapping.get(method).get(url);

		return (command != null) ? command : NOT_FOUND_404_COMMAND;
	}
}
