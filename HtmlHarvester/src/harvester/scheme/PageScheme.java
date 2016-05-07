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
	
	public PageScheme(Node p) throws SchemeParseError{
		Element page = (Element) p;
		datas = new TreeMap<>();
		
		name = page.getAttribute("name");
		
		NodeList nldatas = page.getElementsByTagName("data");
		
		for(int i = 0; i < nldatas.getLength(); i++){
			IDataScheme data = new DataScheme(nldatas.item(i));
			datas.put(data.getDataName(), data);
		}
	}
	
	@Override
	public String getPageName() {
		return name;
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
