package harvester.scheme;

import java.util.Set;

/**
 * Data scheme
 * @author nyradr
 */
public interface IDataScheme {
	
	/**
	 * Get data name
	 * @return
	 */
	public String getDataName();
	
	/**
	 * True if all result of the XPath is returned<br>
	 * False if only the first result of the XPath is returned
	 * @return
	 */
	public boolean isAll();
	
	/**
	 * Get data type
	 * @return
	 */
	public DataType getType();
	
	/**
	 * Get all possible XPath for this data
	 * @return
	 */
	public Set<String> getAllXPath();
}
