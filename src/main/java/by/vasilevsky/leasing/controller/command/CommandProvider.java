package by.vasilevsky.leasing.controller.command;

public interface CommandProvider {
	
	Command getCommand(String urlMapping);
	
	static CommandProvider getInstance() {
		
		return CommandProviderImpl.getInstance();
	}
}
