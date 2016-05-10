package harvester.scheme;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Implementation of {@link IDataScheme}
 * @author nyradr
 */
class DataScheme implements IDataScheme{
	
	private class Pair<T, U>{
		public T fst;
		public U snd;
		
		public Pair(T f, U s){
			fst = f;
			snd = s;
		}
	}
	
	private String name;
	private boolean all;
	private DataType type;
	private XPath xpath;
	
	/**
	 * DataScheme constructor
	 * @param d node element
	 * @throws SchemeParseError
	 */
	public DataScheme(Node d) throws SchemeParseError{
		Element data = (Element) d;
		
		extractAtt(data);
		
		xpath = new XPath(d);
	}
	
	/**
	 * Extract data node element attributes
	 * @param e node element
	 */
	private void extractAtt(Element e) throws SchemeParseError{
		name = e.getAttribute("name");
		all = e.getAttribute("all").equals("1");
		type = DataType.fromString(e.getAttribute("type"));
		
		if(type == null)
			throw new SchemeParseError("Invalid data type");
	}

	@Override
	public String getDataName() {
		return name;
	}

	@Override
	public boolean isAll() {
		return all;
	}

	@Override
	public DataType getType() {
		return type;
	}

	@Override
	public Set<String> getAllXPath() {
		return xpath.getAllXPath();
	}

}
