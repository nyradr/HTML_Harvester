package harvester.scheme;

import java.util.Map;
import java.util.Set;

/**
 * Represent a form scheme
 * @author nyradr
 */
public interface IFormScheme{
	
	/**
	 * Get the form scheme name
	 * @return
	 */
	public String getFormName();
	
	/**
	 * Get XPath access to the form
	 * @return
	 */
	public Set<String> getFormXPaths();
	
	/**
	 * Get all referenced form input fields.<br>
	 * @return Map with input field name as key and default value as value.
	 */
	public Map<String, String> getInputsFields();
	
	/**
	 * True if the submit XPath is relative to the form element
	 * @return
	 */
	public boolean isSubmitRelative();
	
	/**
	 * Get all XPath for getting the submit button
	 * @return
	 */
	public Set<String> getSubmitXPath();
	
	/**
	 * Get the result page scheme
	 * @return
	 */
	public IPageScheme getResultPage();
}
