package harvester.scheme;

import java.io.File;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
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
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(true);
			dbf.setNamespaceAware(true);
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml);
			
			NodeList nlpages = doc.getElementsByTagName("page");
			
			for(int i = 0; i < nlpages.getLength(); i++){
				IPageScheme page = new PageScheme(nlpages.item(i));
				pages.put(page.getPageName(), page);
			}
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Get all pages name
	 * @return
	 */
	public Set<String> getPagesName(){
		return pages.keySet();
	}
	
	/**
	 * Get page scheme from page name
	 * @param name
	 * @return page scheme or null
	 */
	public IPageScheme getPage(String name){
		return pages.get(name);
	}
}
