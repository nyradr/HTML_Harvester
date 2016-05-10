package harvester.scheme;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XPath management class
 * @author nyradr
 */
class XPath {
	
	private class Pair<T, U>{
		public T fst;
		public U snd;
		
		public Pair(T f, U s){
			fst = f;
			snd = s;
		}
	}
	
	private String baseXPath;
	private Map<String, Set<String>> args;
	
	private Set<String> allXPath;
	
	public XPath(Node parent) throws SchemeParseError{
		Element elem = (Element) parent;
		args = new TreeMap<>();
		allXPath = new HashSet<>();
		
		extractBase(elem);
		extractArgs(elem);
		
		for(Set<Pair<String, String>> pos : buildAllXpath(new HashSet<>())){
			String xpath = baseXPath;
			
			for(Pair<String, String> e : pos)
				xpath = xpath.replace(e.fst, e.snd);
			
			allXPath.add(xpath);
		}
	}
	
	/**
	 * Extract base XPath value from parent element
	 * @param e parent element
	 * @throws SchemeParseError
	 */
	private void extractBase(Element e) throws SchemeParseError{
		// get xpath value
		try{
			NodeList nlxp = e.getElementsByTagName("xpath");
			baseXPath = nlxp.item(0).getTextContent();
		}catch(Exception ex){
			throw new SchemeParseError("Data without XPAth");
		}
	}
	
	/**
	 * Extract XPath arguments from parent element
	 * @param n
	 * @throws SchemeParseError
	 */
	private void extractArgs(Element e) throws SchemeParseError{
		args = new TreeMap<>();
		NodeList nlargs = e.getElementsByTagName("xargs");
		
		for(int i = 0; i < nlargs.getLength(); i++){
			Element arg = (Element) nlargs.item(i);
			
			String arg_name = arg.getAttribute("id");
			if(arg_name.isEmpty())
				throw new SchemeParseError("XPath argument without id");
			
			Set<String > arg_val = new HashSet<>();
			
			NodeList nlval = arg.getElementsByTagName("arg");
			for(int j = 0; j < nlval.getLength(); j++)
				arg_val.add(nlval.item(j).getTextContent());
			
			args.put(arg_name, arg_val);
		}
	}
	
	/**
	 * Build all XPath values
	 * @param done
	 * @return
	 */
	private Set<Set<Pair<String, String>>> buildAllXpath(Set<String> done){
		Set<Set<Pair<String, String>>> ret = new HashSet<>();
		
		for(String key : args.keySet()){
			if(!done.contains(key)){
				done.add(key);
				
				for(String val : args.get(key)){
					Pair<String, String> dkv = new Pair(key, val);
					
					Set<Set<Pair<String, String>>> prev = buildAllXpath(done);
					
					if(prev.isEmpty()){
						Set<Pair<String, String>> init = new HashSet<>();
						init.add(dkv);
						ret.add(init);
					}else{
						for(Set<Pair<String, String>> preve : prev){
							preve.add(dkv);
							ret.add(preve);
						}
					}
				}
				
				done.remove(key);
			}
		}
		
		return ret;
	}
	
	public Set<String> getAllXPath(){
		return allXPath;
	}
}
