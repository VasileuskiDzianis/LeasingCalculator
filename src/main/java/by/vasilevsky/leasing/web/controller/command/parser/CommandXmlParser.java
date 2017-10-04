package by.vasilevsky.leasing.web.controller.command.parser;

import java.util.Map;

import by.vasilevsky.leasing.web.controller.command.Command;

public interface CommandXmlParser {

	Map<String, Map<String, Command>> getCommandsMapping();
}