package by.vasilevsky.leasing.web.controller.command;

public class CommandException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CommandException() {
	}

	public CommandException(String message) {
		super(message);
	}

	public CommandException(Throwable cause) {
		super(cause);
	}

	public CommandException(String message, Throwable cause) {
		super(message, cause);
	}

	public CommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
