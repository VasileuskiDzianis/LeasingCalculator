package by.vasilevsky.leasing.view;

public class RegistrationFormModel {
	private String mainMessage;
	private String loginMessage;
	private String firstPasswordMessage;
	private String secondPasswordMessage;

	private String login;
	private String firstPassword;
	private String secondPassword;

	private boolean errorsExist;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstPassword() {
		return firstPassword;
	}

	public void setFirstPassword(String firstPassword) {
		this.firstPassword = firstPassword;
	}

	public String getSecondPassword() {
		return secondPassword;
	}

	public void setSecondPassword(String secondPassword) {
		this.secondPassword = secondPassword;
	}

	public boolean isErrorsExist() {
		return errorsExist;
	}

	public void setHasErrors(boolean hasErrors) {
		this.errorsExist = hasErrors;
	}

	public String getMainMessage() {
		return mainMessage;
	}

	public void setMainMessage(String mainMessage) {
		this.mainMessage = mainMessage;
	}

	public String getLoginMessage() {
		return loginMessage;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}

	public String getFirstPasswordMessage() {
		return firstPasswordMessage;
	}

	public void setFirstPasswordMessage(String firstPasswordMessage) {
		this.firstPasswordMessage = firstPasswordMessage;
	}

	public String getSecondPasswordMessage() {
		return secondPasswordMessage;
	}

	public void setSecondPasswordMessage(String secondPasswordMessage) {
		this.secondPasswordMessage = secondPasswordMessage;
	}
}
