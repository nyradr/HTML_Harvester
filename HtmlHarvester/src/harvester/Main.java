package harvester;

import java.io.File;

import harvester.data.IData;
import harvester.data.IPage;
import harvester.scheme.IDataScheme;
import harvester.scheme.IPageScheme;
import harvester.scheme.Scheme;

/**
 * Debug main
 * @author nyradr
 *
 */
class Main {
	
	/**
	 * Draw a scheme informations
	 * @param sc
	 */
	private static void drawScheme(Scheme sc){
		System.out.println("SCHEME : ");
		
		for(String pn : sc.getPagesName()){
			IPageScheme page = sc.getPage(pn);
			
			System.out.println("Page : " + page.getPageName());
			
			for(String dn : page.getDatasName()){
				IDataScheme data = page.getData(dn);
				
				System.out.println("\tData : " + data.getDataName());
				System.out.println("\t\tAll : " + data.isAll());
				System.out.println("\t\tType : " + data.getType().toString());
				
				for(String xp : data.getAllXPath())
					System.out.println("\t\tXPath : " + xp);
			}
		}
	}
	
	/**
	 * Load and draw a scheme
	 * @param file
	 */
	private static void testScheme(String file){
		try{
			Scheme sc = new Scheme(new File(file));
			drawScheme(sc);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Draw page result
	 * @param page
	 */
	private static void drawPageResult(IPage page){
		System.out.println("PAGE : ");
		
		System.out.println("P : " + page.getName());
		
		for(String dn : page.getDatasName()){
			IData data = page.getByName(dn);
			
			System.out.println("\tD : " + dn);
			
			for(String res : data.getTexts()){
				System.out.println("\t\tR : " + res);
			}
		}
	}
	
	/**
	 * Harvester test
	 * @param xml
	 * @param page
	 * @param url
	 */
	private static void harvTest(String xml, String page, String url){
		try{
			Scheme sc = new Scheme(new File(xml));
			
			Harvester hv = new Harvester(sc);
			IPage ip = hv.get(url, page);
			
			drawScheme(sc);
			drawPageResult(ip);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String [] a){
		harvTest("xml/test.xml", "ql", "https://lite.qwant.com/?q=qwant&t=web");
	}
}
