package harvester;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.gargoylesoftware.htmlunit.html.DomAttr;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import harvester.data.IData;
import harvester.data.IPage;
import harvester.scheme.DataType;
import harvester.scheme.IDataScheme;
import harvester.scheme.IPageScheme;

/**
 * Page scanner
 * @author nyradr
 */
class PageProcessor {
	
	/**
	 * Implementation of {@link IData}
	 * @author nyradr
	 */
	private class Data implements IData{
		private IDataScheme scheme;
		private List<String> datas;
		
		public Data(IDataScheme scheme){
			this.scheme = scheme;
			datas = new ArrayList<>();
		}
		
		/**
		 * Add data to list
		 * @param dt
		 */
		public void addData(String dt){
			datas.add(dt);
		}
		
		/**
		 * Filter all data following a predicate
		 * @param p
		 */
		public void filter(Predicate<? super String> p){
			datas = datas.stream()
					.filter(p)
					.collect(Collectors.toList());
		}
		
		@Override
		public IDataScheme getScheme() {
			return scheme;
		}

		@Override
		public List<String> getTexts() {
			return datas;
		}

		@Override
		public String getText() {
			if(!scheme.isAll() && !datas.isEmpty())
				return datas.get(0);
			
			return null;
		}
		
	}
	
	/**
	 * Implementation of {@link IPage}
	 * @author nyradr
	 */
	private class Page implements IPage{
		private IPageScheme scheme;
		private Map<String, IData> datas;
		
		public Page(IPageScheme scheme) {
			this.scheme = scheme;
			datas = new TreeMap<>();
			
			for(String name : scheme.getDatasName())
				datas.put(name, null);
		}
		
		/**
		 * Add data to harvested data
		 * @param name data name
		 * @param dt data value
		 */
		public void addData(String name, IData dt){
			datas.put(name, dt);
		}
		
		@Override
		public String getName() {
			return scheme.getPageName();
		}

		@Override
		public IPageScheme getScheme() {
			return scheme;
		}

		@Override
		public Set<String> getDatasName() {
			return datas.keySet();
		}

		@Override
		public List<IData> getAll() {
			return (List<IData>) datas.values();
		}

		@Override
		public IData getByName(String name) {
			return datas.get(name);
		}
		
	}
	
	/**
	 * Extract data from HTML page
	 * @param html HTML page
	 * @param scheme data scheme
	 * @return harvested data
	 */
	private IData extractData(HtmlPage html, IDataScheme scheme){
		Data data = new Data(scheme);
		
		// test all XPaths
		for(String xpath : scheme.getAllXPath()){
			
			
			// get all results
			for(Object o : html.getByXPath(xpath)){
				
				// result type is attribute
				if(scheme.getType() == DataType.ATT){
					DomAttr att = (DomAttr) o;
					data.addData(att.getValue());
					
				}else{	// non attribute result
					DomElement elem = (DomElement) o;
					
					switch (scheme.getType()) {
					case TXT:	// get text nodes values
						data.addData(elem.asText());
						break;

					case XML: // get XHTML value
						data.addData(elem.asXml());
						break;
					default:
						break;
					}
				}
			}
		}
		
		return data;
	}
	
	/**
	 * Scan HTML page and extract informations
	 * @param html HTML page
	 * @param scheme page scheme
	 * @return data instance of harvested data
	 */
	public IPage scanPage(HtmlPage html, IPageScheme scheme){
		Page page = new Page(scheme);
		
		for(String idn : scheme.getDatasName()){
			IDataScheme ids = scheme.getData(idn);
			page.addData(ids.getDataName(), extractData(html, ids));
		}
		
		return page;
	}
	
	
}
