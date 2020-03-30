package exception;

public class LongNameException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Name can't be longer \nthan 10 characters";
	}
	
}
