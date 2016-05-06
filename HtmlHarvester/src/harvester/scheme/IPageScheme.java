package harvester.scheme;

import java.util.Set;

/**
 * Page scheme interface
 * @author nyradr
 */
public interface IPageScheme {
	
	/**
	 * Get the page name
	 * @return
	 */
	public String getPageName();
	
	/**
	 * Get page data names
	 * @return set of data names
	 */
	public Set<String> getDatasName();
	
	/**
	 * Get data scheme from name
	 * @param name data name
	 * @return data scheme or null
	 */
	public IDataScheme getData(String name);
}
