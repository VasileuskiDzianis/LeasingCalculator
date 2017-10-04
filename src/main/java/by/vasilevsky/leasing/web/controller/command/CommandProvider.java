package by.vasilevsky.leasing.web.controller.command;

public interface CommandProvider {
	
	Command getCommand(String method, String url);
	
	static CommandProvider getInstance() {
		
		return CommandProviderImpl.getInstance();
	}
}
