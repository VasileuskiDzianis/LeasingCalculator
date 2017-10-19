package by.vasilevsky.leasing.service.exception;

public class IllegalArgumentServiceException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;

	public IllegalArgumentServiceException() {
	}

	public IllegalArgumentServiceException(String s) {
		super(s);
	}

	public IllegalArgumentServiceException(Throwable cause) {
		super(cause);
	}

	public IllegalArgumentServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}
