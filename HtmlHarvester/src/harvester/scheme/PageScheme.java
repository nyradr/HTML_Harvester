package harvester.scheme;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Page scheme implementation
 * @author nyradr
 */
class PageScheme implements IPageScheme{
	
	private String name;
	private Map<String, IDataScheme> datas;
	
	/**
	 * Create page scheme from XML element
	 * @param p XML node
	 * @throws SchemeParseError
	 */
	public PageScheme(Node p) throws SchemeParseError{
		Element page = (Element) p;
		datas = new TreeMap<>();
		
		name = page.getAttribute("name");
		
		NodeList nldatas = page.getElementsByTagName("data");
		
		for(int i = 0; i < nldatas.getLength(); i++){
			IDataScheme data = new DataScheme(nldatas.item(i));
			addData(data);
		}
	}
	
	/**
	 * Create empty page scheme
	 * @param name page name
	 */
	public PageScheme(String name) {
		this.name = name;
		datas = new TreeMap<>();
	}
	
	@Override
	public String getPageName() {
		return name;
	}
	
	public void addData(IDataScheme data){
		datas.put(data.getDataName(), data);
	}

	@Override
	public Set<String> getDatasName() {
		return datas.keySet();
	}

	@Override
	public IDataScheme getData(String name) {
		return datas.get(name);
	}

}
