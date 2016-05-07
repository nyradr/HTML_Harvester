package harvester.scheme;

/**
 * Scheme parse error
 * @author nyradr
 */
public class SchemeParseError extends Exception{
	
	public SchemeParseError(){
		
	}
	
	public SchemeParseError(String mess){
		super(mess);
	}
	
	public SchemeParseError(Exception e){
		super(e.getMessage());
	}
}
