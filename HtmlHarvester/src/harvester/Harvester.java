package harvester;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import harvester.data.IData;
import harvester.data.IPage;
import harvester.scheme.IFormScheme;
import harvester.scheme.IPageScheme;
import harvester.scheme.Scheme;

/**
 * Web page data harvester<br>
 * Can extract data from a HTML page with a page scheme
 * @author nyradr
 */
public class Harvester {
	
	private Scheme scheme;
	private WebClient browser;
	private PageProcessor pagep;
	private FormProcessor formp;
	
	public Harvester(Scheme s){
		scheme = s;
		browser = new WebClient();
		pagep = new PageProcessor();
		formp = new FormProcessor();
	}
	
	public Harvester(Scheme s, WebClient c){
		scheme = s;
		browser = c;
	}
	
	/**
	 * Load and scan HTML page from URL
	 * @param url page url (eventually with GET arguments)
	 * @param page page name to scan
	 * @return IPage interface containing scanned data
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FailingHttpStatusCodeException 
	 */
	public IPage get(String url, String page) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		HtmlPage doc = browser.getPage(url);
		
		return get(doc, page);
	}
	
	/**
	 * Scan HTML page
	 * @param doc HTML document to scan
	 * @param page page name to scan
	 * @return IPage interface containing scanned data
	 */
	public IPage get(HtmlPage doc, String page){
		IPageScheme ps = scheme.getPage(page);
		IPage ip = null;
		
		if(ps != null)
			ip =  pagep.scanPage(doc, ps);
		
		return ip;
	}
	
	/**
	 * Fill and submit HTML page from page URL
	 * @param url page URL
	 * @param form form scheme name
	 * @param inp input user defined values
	 * @return submitting result IPage or null
	 * @throws FailingHttpStatusCodeException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public IPage submit(String url, String form, Map<String, String> inp) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		HtmlPage doc = browser.getPage(url);
		
		return submit(doc, form, inp);
	}
	
	/**
	 * Fill and submit HTML page
	 * @param doc HTML page
	 * @param form form scheme name
	 * @param inp input user defined values
	 * @return submitting result IPage or null
	 * @throws IOException
	 */
	public IPage submit(HtmlPage doc, String form, Map<String, String> inp) throws IOException{
		IFormScheme fs = scheme.getForm(form);
		IPage ip = null;
		
		if(fs != null)
			ip = formp.fillForm(doc, fs, inp);
		
		return ip;
	}
	
	/**
	 * Get loaded scheme
	 * @return
	 */
	public Scheme getScheme(){
		return scheme;
	}
	
	/**
	 * Set new scheme
	 * @param s
	 */
	public void setScheme(Scheme s){
		scheme = s;
	}
	
	/**
	 * Define new web browser
	 * @param c
	 */
	public void setBrowser(WebClient c){
		browser = c;
	}
}
