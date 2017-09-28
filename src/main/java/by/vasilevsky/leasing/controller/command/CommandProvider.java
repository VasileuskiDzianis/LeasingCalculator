package by.vasilevsky.leasing.controller.command;

public interface CommandProvider {
	
	Command getCommand(String method, String url);
	
	static CommandProvider getInstance() {
		
		return CommandProviderImpl.getInstance();
	}
}
