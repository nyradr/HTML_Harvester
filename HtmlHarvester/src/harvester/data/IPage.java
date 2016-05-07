package harvester.data;

import java.util.List;
import java.util.Set;

import harvester.scheme.IPageScheme;

/**
 * Scanned page
 * @author nyradr
 */
public interface IPage {
	
	/**
	 * Get the page name
	 * @return
	 */
	public String getName();
	
	/**
	 * Get the page scheme
	 * @return
	 */
	public IPageScheme getScheme();
	
	/**
	 * Get the page data names
	 * @return
	 */
	public Set<String> getDatasName();
	
	/**
	 * Get a list of all collected data
	 * @return
	 */
	public List<IData> getAll();
	
	/**
	 * Get data by data name
	 * @param name
	 * @return
	 */
	public IData getByName(String name);
}
