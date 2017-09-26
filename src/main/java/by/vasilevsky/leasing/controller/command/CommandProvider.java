package by.vasilevsky.leasing.controller.command;

import by.vasilevsky.leasing.controller.command.implementation.CommandProviderImpl;

public interface CommandProvider {
	
	Command getCommand(String urlMapping);
	
	static CommandProvider getInstance() {
		
		return CommandProviderImpl.getInstance();
	}
}
