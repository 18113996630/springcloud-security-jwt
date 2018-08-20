package org.hrong.springcloudsecurityjwt.exception;

public class TokenException extends RuntimeException {
	private static final long serialVersionUID = -6332894765693495654L;

	public TokenException() {
		super();
	}

	public TokenException(String message) {
		super(message);
	}
}
