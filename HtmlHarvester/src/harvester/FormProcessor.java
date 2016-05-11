package harvester;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import harvester.data.IPage;
import harvester.scheme.IFormScheme;

/**
 * Processor for form filling and submitting
 * @author nyradr
 */
class FormProcessor {
	
	/**
	 * Get fist form in the page corresponding with scheme XPath
	 * @param page HTML page
	 * @param scheme page scheme
	 * @return HTML form or null if no form found
	 */
	private HtmlForm getForm(HtmlPage page, IFormScheme scheme){
		List<Object> poss = new ArrayList<>();
		
		for(String xp : scheme.getFormXPaths())
			poss.addAll(page.getByXPath(xp));
		
		for(Object o : poss){
			DomElement elem = (DomElement) o;
			if(elem.getTagName().toLowerCase().equals("form"))
				return (HtmlForm) elem;
		}
		
		return null;
	}
	
	/**
	 * Get fist HTML element corresponding to submit XPath
	 * @param page HTML page
	 * @param form HTML form
	 * @param scheme form scheme
	 * @return submit HTML element or null
	 */
	private HtmlElement getSubmit(HtmlPage page, HtmlForm form, IFormScheme scheme){
		List<Object> poss = new ArrayList<>();
		
		for(String xp : scheme.getSubmitXPath()){
			if(scheme.isSubmitRelative())
				poss.addAll(form.getByXPath(xp));
			else
				poss.addAll(page.getByXPath(xp));
		}
		
		HtmlElement elem = null;
		
		if(!poss.isEmpty())
			elem = (HtmlElement) poss.get(0);
		
		return elem;
	}
	
	/**
	 * Fill and submit HTML form from HTML page
	 * @param page HTML page
	 * @param scheme Form page scheme
	 * @param inputs input field user defined values
	 * @return IPage resulting of the form submitting or null
	 * @throws IOException
	 */
	public IPage fillForm(HtmlPage page, IFormScheme scheme, Map<String, String> inputs) throws IOException{
		HtmlForm form = getForm(page, scheme);
		
		if(form != null){
			for(String inp : scheme.getInputsFields().keySet()){
				HtmlInput input = form.getInputByName(inp);
				if(inputs.containsKey(inp))
					input.setValueAttribute(inputs.get(inp));
				else
					input.setValueAttribute(scheme.getInputsFields().get(inp));
			}
			
			HtmlElement elem = getSubmit(page, form, scheme);
			if(elem != null){
				PageProcessor pp = new PageProcessor();
				
				return pp.scanPage(elem.click(), scheme.getResultPage());
			}
		}
		
		return null;
	}
}
