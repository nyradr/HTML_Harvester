package harvester.data;

import java.util.List;

import harvester.scheme.IDataScheme;

/**
 * Scanned data
 * @author nyradr
 */
public interface IData {
	
	/**
	 * Data scheme
	 * @return
	 */
	public IDataScheme getScheme();
	
	/**
	 * If all result option is selected a list of all result is returned.<br>
	 * If all result option isn't selected the value is in the first element of the list<br>
	 * If the XPath is invalid or there is no available values, an empty list is returned.
	 * @return
	 */
	public List<String> getTexts();
	
	/**
	 * If all result option isn't selected return the value.<br>
	 * Else return null.<br>
	 * If the XPath is invalid or there is no available values, an empty string is returned.
	 * @return
	 */
	public List<String> getText();
}
