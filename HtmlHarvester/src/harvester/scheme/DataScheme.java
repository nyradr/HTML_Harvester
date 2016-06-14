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
	 * Build data scheme from XML element
	 * @param d node element
	 * @throws SchemeParseError
	 */
	public DataScheme(Node d) throws SchemeParseError{
		Element data = (Element) d;
		
		extractAtt(data);
		
		xpath = new XPath(d);
	}
	
	/**
	 * Build data scheme
	 * @param name scheme name
	 * @param all 	if true if all element match the XPath is harvested<br>
	 * 				if false only the fist element matching the XPath is harvested
	 * @param type data type
	 * @param xp XPath
	 */
	public DataScheme(String name, boolean all, DataType type, XPath xp){
		this.name = name;
		this.all = all;
		this.type = type;
		xpath = xp;
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
