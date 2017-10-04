package by.vasilevsky.leasing.controller.command;

import java.util.Map;

import by.vasilevsky.leasing.controller.command.impl.*;
import by.vasilevsky.leasing.controller.command.parser.CommandXmlParser;
import by.vasilevsky.leasing.controller.command.parser.CommandXmlParserImpl;

public final class CommandProviderImpl implements CommandProvider {
	private static volatile CommandProviderImpl instance;

	private static final Command NOT_FOUND_404_COMMAND = new NotFound404Command();

	private final Map<String, Map<String, Command>> commandMapping;

	private CommandProviderImpl() {
		CommandXmlParser parser = new CommandXmlParserImpl();
		commandMapping = parser.getCommandsMapping();
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
