package exception;

public class SpecialCharacterException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String text;
	public SpecialCharacterException(String text) {
		this.text = text;
	}
	
	@Override
	public String getMessage() {
		return "Character [ " + text + " ] is not allowed.";
	}
}
