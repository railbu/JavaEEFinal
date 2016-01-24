package service;

public class LoginFailureException extends RuntimeException {

	public LoginFailureException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 8314300433515394719L;

}
