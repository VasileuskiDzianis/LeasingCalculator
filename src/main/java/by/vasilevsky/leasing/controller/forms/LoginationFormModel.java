package by.vasilevsky.leasing.controller.forms;

public class LoginationFormModel {
	private String mainMessage;

	private String login;
	private String password;

	private boolean errors;

	public String getMainMessage() {
		return mainMessage;
	}

	public void setMainMessage(String mainMessage) {
		this.mainMessage = mainMessage;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean hasErrors() {
		return errors;
	}

	public void setErrorsExist(boolean errors) {
		this.errors = errors;
	}
}
