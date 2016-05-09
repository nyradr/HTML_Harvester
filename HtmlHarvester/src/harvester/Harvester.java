package harvester;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import harvester.data.IData;
import harvester.data.IPage;
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
	
	public Harvester(Scheme s){
		scheme = s;
		browser = new WebClient();
		pagep = new PageProcessor();
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
