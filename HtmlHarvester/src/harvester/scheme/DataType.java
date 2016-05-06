package harvester.scheme;

/**
 * Data types
 * @author nyradr
 */
public enum DataType {
	ATT		("att"),
	TXT		("txt"),
	XML		("xml");
	
	private String val;
	
	private DataType(String s){
		val = s;
	}
	
	public String toString(){
		return val;
	}
	
	/**
	 * Parse DataType from String
	 * @param str string value
	 * @return DataType or null
	 */
	public static DataType fromString(String str){
		for(DataType dt : DataType.values())
			if(dt.toString().equals(str))
				return dt;
		
		return null;
	}
}
