package by.vasilevsky.leasing.controller.forms;

public class ProfileFormModel {
	public static final String ALIAS = "profileFormModel";
	
	private String userId;
	private String detailsId;
	private String firstName;
	private String lastName;
	private String age;

	private String firstNameMessage;
	private String lastNameMessage;
	private String ageMessage;

	private String mainMessage;

	public String getMainMessage() {
		return mainMessage;
	}

	public void setMainMessage(String mainMessage) {
		this.mainMessage = mainMessage;
	}

	private boolean errors;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(String detailsId) {
		this.detailsId = detailsId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFirstNameMessage() {
		return firstNameMessage;
	}

	public void setFirstNameMessage(String firstNameMessage) {
		this.firstNameMessage = firstNameMessage;
	}

	public String getLastNameMessage() {
		return lastNameMessage;
	}

	public void setLastNameMessage(String lastNameMessage) {
		this.lastNameMessage = lastNameMessage;
	}

	public String getAgeMessage() {
		return ageMessage;
	}

	public void setAgeMessage(String ageMessage) {
		this.ageMessage = ageMessage;
	}

	public boolean hasErrors() {
		return errors;
	}

	public void setErrorsExist(boolean errors) {
		this.errors = errors;
	}
}
