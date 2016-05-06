package harvester.scheme;

import java.io.File;
import java.util.Map;
import java.util.Set;

import org.xml.sax.InputSource;

/**
 * HTML harvester pages scheme
 * @author nyradr
 */
public class Scheme {
	
	private Map<String, IPageScheme> pages;
	
	/**
	 * Create Scheme from XML string
	 * @param xml XML as string
	 * @throws SchemeParseError
	 */
	public Scheme(String xml) throws SchemeParseError{
		
	}
	
	/**
	 * Create Scheme from source
	 * @param xml XML as source
	 * @throws SchemeParseError
	 */
	public Scheme(InputSource xml) throws SchemeParseError{
		
	}
	
	/**
	 * Create Scheme from file
	 * @param xml XML file
	 * @throws SchemeParseError
	 */
	public Scheme(File xml) throws SchemeParseError{
		
	}
	
	/**
	 * Load scheme from XML
	 * @param xml XML as source
	 */
	private void loadXML(InputSource xml) throws SchemeParseError{
		
	}
	
	/**
	 * Get all pages name
	 * @return
	 */
	public Set<String> getPagesName(){
		return null;
	}
	
	/**
	 * Get page scheme from page name
	 * @param name
	 * @return page scheme or null
	 */
	public IPageScheme getPage(String name){
		return null;
	}
}
