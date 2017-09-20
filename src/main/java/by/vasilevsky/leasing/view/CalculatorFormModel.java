package by.vasilevsky.leasing.view;

public class CalculatorFormModel {
	private String currency;
	private String objectType;
	private String age;
	private String cost;
	private String noVatOnCost;
	private String prepay;
	private String duration;
	private String byuingout;
	private String insurance;

	private String currencyMessage;
	private String objectTypeMessage;
	private String ageMessage;
	private String costMessage;
	private String noVatOnCostMessage;
	private String prepayMessage;
	private String durationMessage;
	private String byuingoutMessage;
	private String insuranceMessage;

	private String mainMessage;
	private boolean errorsExist;

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getNoVatOnCost() {
		return noVatOnCost;
	}

	public void setNoVatOnCost(String noVatOnCost) {
		this.noVatOnCost = noVatOnCost;
	}

	public String getPrepay() {
		return prepay;
	}

	public void setPrepay(String prepay) {
		this.prepay = prepay;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getByuingout() {
		return byuingout;
	}

	public void setByuingout(String byuingout) {
		this.byuingout = byuingout;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getCurrencyMessage() {
		return currencyMessage;
	}

	public void setCurrencyMessage(String currencyMessage) {
		this.currencyMessage = currencyMessage;
	}

	public String getObjectTypeMessage() {
		return objectTypeMessage;
	}

	public void setObjectTypeMessage(String objectTypeMessage) {
		this.objectTypeMessage = objectTypeMessage;
	}

	public String getAgeMessage() {
		return ageMessage;
	}

	public void setAgeMessage(String ageMessage) {
		this.ageMessage = ageMessage;
	}

	public String getCostMessage() {
		return costMessage;
	}

	public void setCostMessage(String costMessage) {
		this.costMessage = costMessage;
	}

	public String getNoVatOnCostMessage() {
		return noVatOnCostMessage;
	}

	public void setNoVatOnCostMessage(String noVatOnCostMessage) {
		this.noVatOnCostMessage = noVatOnCostMessage;
	}

	public String getPrepayMessage() {
		return prepayMessage;
	}

	public void setPrepayMessage(String prepayMessage) {
		this.prepayMessage = prepayMessage;
	}

	public String getDurationMessage() {
		return durationMessage;
	}

	public void setDurationMessage(String durationMessage) {
		this.durationMessage = durationMessage;
	}

	public String getByuingoutMessage() {
		return byuingoutMessage;
	}

	public void setByuingoutMessage(String byuingoutMessage) {
		this.byuingoutMessage = byuingoutMessage;
	}

	public String getInsuranceMessage() {
		return insuranceMessage;
	}

	public void setInsuranceMessage(String insuranceMessage) {
		this.insuranceMessage = insuranceMessage;
	}

	public String getMainMessage() {
		return mainMessage;
	}

	public void setMainMessage(String mainMessage) {
		this.mainMessage = mainMessage;
	}

	public boolean isErrorsExist() {
		return errorsExist;
	}

	public void setErrorsExist(boolean errorsExist) {
		this.errorsExist = errorsExist;
	}
}
