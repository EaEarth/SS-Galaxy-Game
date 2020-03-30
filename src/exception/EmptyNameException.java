package exception;

public class EmptyNameException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "Name can't be empty";
	}
	
}
