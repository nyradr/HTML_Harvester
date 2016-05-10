package harvester.scheme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xml.utils.DefaultErrorHandler;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * HTML harvester pages scheme
 * @author nyradr
 */
public class Scheme {
	
	private Map<String, IPageScheme> pages;
	private Map<String, IFormScheme> forms;
	
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
		forms = new TreeMap<>();
		
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			dbf.setValidating(true);
			dbf.setNamespaceAware(true);
			
			DocumentBuilder db = dbf.newDocumentBuilder();
			db.setErrorHandler(new DefaultErrorHandler());
			
			Document doc = db.parse(xml);
			
			extractPages(doc);
			extractForms(doc);
			
		}catch (Exception e){
			throw new SchemeParseError(e);
		}
	}
	
	/**
	 * Extract all pages from XML scheme document
	 * @param doc
	 * @throws SchemeParseError
	 */
	private void extractPages(Document doc) throws SchemeParseError{
		NodeList nlpages = doc.getElementsByTagName("page");
		
		for(int i = 0; i < nlpages.getLength(); i++){
			IPageScheme page = new PageScheme(nlpages.item(i));
			pages.put(page.getPageName(), page);
		}
	}
	
	/**
	 * Extract all form from XML scheme document
	 * @param doc
	 * @throws SchemeParseError
	 */
	private void extractForms(Document doc) throws SchemeParseError{
		NodeList nl = doc.getElementsByTagName("form");
		
		for(int i = 0; i < nl.getLength(); i++){
			IFormScheme form = new FormScheme(nl.item(i), pages);
			forms.put(form.getFormName(), form);
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
	
	/**
	 * Get all forms name
	 * @return
	 */
	public Set<String> getFormsName(){
		return forms.keySet();
	}
	
	/**
	 * Get form scheme from form name
	 * @param name
	 * @return
	 */
	public IFormScheme getForm(String name){
		return forms.get(name);
	}
}
