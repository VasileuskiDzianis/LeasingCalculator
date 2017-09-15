package by.vasilevsky.leasing.view;

public class LoginationFormModel {
	private String mainMessage;

	private String login;
	private String password;

	private boolean errorsExist;

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

	public boolean isErrorsExist() {
		return errorsExist;
	}

	public void setErrorsExist(boolean errorsExist) {
		this.errorsExist = errorsExist;
	}
}
