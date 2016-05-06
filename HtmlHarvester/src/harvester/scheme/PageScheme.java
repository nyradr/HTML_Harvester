package harvester.scheme;

import java.util.Map;
import java.util.Set;

/**
 * Page scheme implementation
 * @author nyradr
 */
class PageScheme implements IPageScheme{
	
	private Map<String, IDataScheme> datas;
	
	public PageScheme() throws SchemeParseError{
	}
	
	@Override
	public String getPageName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getDatasName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IDataScheme getData(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
