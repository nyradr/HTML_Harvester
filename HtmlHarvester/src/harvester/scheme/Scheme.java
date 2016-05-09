package harvester.scheme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

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
		loadXML(new InputSource(new StringReader(xml)));
	}
	
	/**
	 * Create Scheme from source
	 * @param xml XML as source
	 * @throws SchemeParseError
	 */
	public Scheme(InputSource xml) throws SchemeParseError{
		loadXML(xml);
	}
	
	/**
	 * Create Scheme from file
	 * @param xml XML file
	 * @throws SchemeParseError
	 * @throws FileNotFoundException 
	 */
	public Scheme(File xml) throws SchemeParseError, FileNotFoundException{
		loadXML(new InputSource(new FileReader(xml)));
	}
	
	/**
	 * Load scheme from XML
	 * @param xml XML as source
	 */
	private void loadXML(InputSource xml) throws SchemeParseError{
		pages = new TreeMap<>();
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
			throw new SchemeParseError(e);
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
