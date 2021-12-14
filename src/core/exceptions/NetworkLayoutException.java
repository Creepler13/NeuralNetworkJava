package core.exceptions;

import core.InputLayer;
import core.OutputLayer;

public class NetworkLayoutException extends Exception{

	public NetworkLayoutException() {
	super("The Network has to start with a "+InputLayer.class+" and end with a "+ OutputLayer.class);
	}
	
}
