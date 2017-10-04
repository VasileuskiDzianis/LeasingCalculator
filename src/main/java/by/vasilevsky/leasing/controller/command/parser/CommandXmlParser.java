package by.vasilevsky.leasing.controller.command.parser;

import java.util.Map;

import by.vasilevsky.leasing.controller.command.Command;

public interface CommandXmlParser {

	Map<String, Map<String, Command>> getCommandsMapping();
}