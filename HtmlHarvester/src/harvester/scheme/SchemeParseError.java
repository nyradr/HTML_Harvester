package harvester.scheme;

/**
 * Scheme parse error<br>
 * Global error handler for each errors produced at Scheme parsing
 * @author nyradr
 */
public class SchemeParseError extends Exception{
	
	public SchemeParseError(){
		
	}
	
	public SchemeParseError(String mess){
		super(mess);
	}
	
	public SchemeParseError(Exception e){
		super(e);
	}
}
