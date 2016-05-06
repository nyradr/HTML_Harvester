package harvester.scheme;

import java.util.Map;
import java.util.Set;

/**
 * Implementation of {@link IDataScheme}
 * @author nyradr
 */
class DataScheme implements IDataScheme{
	
	private String name;
	private boolean all;
	private DataType type;
	private String baseXPath;
	private Set<String> allXPath;
	private Map<String, Set<String>> args;
	
	public DataScheme() throws SchemeParseError{
	}
	
	@Override
	public String getDataName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DataType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getAllXPath() {
		// TODO Auto-generated method stub
		return null;
	}

}
