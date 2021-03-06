package by.vasilevsky.leasing.web.controller.command;

import java.util.Map;

import by.vasilevsky.leasing.web.controller.command.impl.NotFound404Command;
import by.vasilevsky.leasing.web.controller.command.parser.CommandXmlParser;
import by.vasilevsky.leasing.web.controller.command.parser.CommandXmlParserImpl;

public final class CommandProviderImpl implements CommandProvider {
	private static final CommandProviderImpl INSTANCE = new CommandProviderImpl();

	private static final Command NOT_FOUND_404_COMMAND = new NotFound404Command();

	//Map<key1, Map<key2, value>>, where key1 - method type, such as GET or POST; 
	//key2 - url; value - command
	private final Map<String, Map<String, Command>> commandMapping;

	private CommandProviderImpl() {
		CommandXmlParser parser = new CommandXmlParserImpl();
		commandMapping = parser.getCommandsMapping();
	}

	static CommandProvider getInstance() {
	
		return INSTANCE;
	}

	@Override
	public Command getCommand(final String method, String url) {
		Command command = commandMapping.get(method.toLowerCase()).get(url);

		return (command != null) ? command : NOT_FOUND_404_COMMAND;
	}
}
