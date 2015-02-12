package transformation.util;

public class NumTextTransformException extends Exception{
	
	public NumTextTransformException() {
	}

	
	public NumTextTransformException(String message) {
		super(message);
	}
	
	public String getMessage(){
        return super.getMessage();
    }
}