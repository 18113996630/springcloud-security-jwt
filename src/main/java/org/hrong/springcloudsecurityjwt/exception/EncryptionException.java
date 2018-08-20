package org.hrong.springcloudsecurityjwt.exception;

public class EncryptionException extends RuntimeException {
	private static final long serialVersionUID = -2933578064733363812L;

	public EncryptionException() {
		super();
	}

	public EncryptionException(String message) {
		super(message);
	}
}
