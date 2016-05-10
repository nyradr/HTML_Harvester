package harvester.scheme;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Implementation of {@link IFormScheme}
 * @author nyradr
 */
class FormScheme implements IFormScheme{
	
	private String name;
	private XPath xpath;
	private Map<String, String> inputs;
	private boolean isSubRel;
	private XPath submit;
	private IPageScheme result;
	
	public FormScheme(Node d, Map<String, IPageScheme> pages) throws SchemeParseError{
		Element elem = (Element) d;
		
		inputs = new TreeMap<>();
		
		name = elem.getAttribute("name");
		if(name.isEmpty())
			throw new SchemeParseError("Invalid form name");
		
		extractXPath(elem);
		extractInput(elem);
		extractSubmit(elem);
		extractResult(elem, pages);
	}
	
	/**
	 * Extract XPath form path
	 * @param e
	 * @throws SchemeParseError
	 */
	private void extractXPath(Element e) throws SchemeParseError{
		try{
			NodeList nl = e.getElementsByTagName("path");
			xpath = new XPath(nl.item(0));
		}catch (SchemeParseError se){
			throw se;
		}catch (Exception ex){
			throw new SchemeParseError(ex);
		}
	}
	
	/**
	 * Extract input values
	 * @param e
	 * @throws SchemeParseError
	 */
	private void extractInput(Element e) throws SchemeParseError{
		NodeList nl = e.getElementsByTagName("input");
		
		for(int i = 0; i < nl.getLength(); i++){
			Element inp = (Element) nl.item(i);
			
			String name = inp.getAttribute("name");
			String def = inp.getTextContent();
			
			if(name.isEmpty())
				throw new SchemeParseError("Empty input field name");
			
			inputs.put(name, def);
		}
	}
	
	/**
	 * Extract submit button
	 * @param e
	 * @throws SchemeParseError
	 */
	private void extractSubmit(Element e) throws SchemeParseError{
		try{
			NodeList nl = e.getElementsByTagName("submit");
			Element sub = (Element) nl.item(0);
			
			isSubRel = sub.getAttribute("useform").equals("1");
			submit = new XPath(sub);
			
		}catch (SchemeParseError se){
			throw se;
		}catch (Exception ex){
			throw new SchemeParseError(ex);
		}
		 
	}
	
	/**
	 * Extract result page
	 * @param e
	 * @param pages
	 * @throws SchemeParseError
	 */
	private void extractResult(Element e, Map<String, IPageScheme> pages) throws SchemeParseError{
		try{
			NodeList nl = e.getElementsByTagName("result");
			Element page = (Element) nl.item(0);
			
			NodeList isPage = e.getElementsByTagName("page");
			if(isPage.getLength() > 0)
				result = new PageScheme(isPage.item(0));
			else{
				result = pages.get(page.getTextContent());
				
				if(result == null)
					throw new SchemeParseError("Unable to find result page");
			}
				
		}catch (SchemeParseError se){
			throw se;
		}catch (Exception ex){
			throw new SchemeParseError(ex);
		}
	}
	
	@Override
	public String getFormName() {
		return name;
	}

	@Override
	public Set<String> getFormXPaths() {
		return xpath.getAllXPath();
	}

	@Override
	public Map<String, String> getInputsFields() {
		return inputs;
	}

	@Override
	public boolean isSubmitRelative() {
		return isSubRel;
	}

	@Override
	public Set<String> getSubmitXPath() {
		return submit.getAllXPath();
	}

	@Override
	public IPageScheme getResultPage() {
		return result;
	}

}
