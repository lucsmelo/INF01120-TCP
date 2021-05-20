package exceptions;

public class ReviewException extends Exception {
	String message;
	public ReviewException (String message) {
		super(message);
	}
}
