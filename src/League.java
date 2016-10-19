import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class League extends Extractor{
	private int id;
	private String name;
	private int totalRounds;
	private int currentRounds;
	//constructors
	public League() {
		super();
	}
	
	public League(int id, String name, int totalRounds, int currentRounds) {
		super();
		this.id = id;
		this.name = name;
		this.totalRounds = totalRounds;
		this.currentRounds = currentRounds;
	}

//getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotalRounds() {
		return totalRounds;
	}
	public void setTotalRounds(int totalRounds) {
		this.totalRounds = totalRounds;
	}
	public int getCurrentRounds() {
		return currentRounds;
	}
	public void setCurrentRounds(int currentRounds) {
		this.currentRounds = currentRounds;
	}

	@Override
	public ArrayList<League> extract(String xml) {
		ArrayList<League> list = new ArrayList<League>();
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String expression = "/leagues/league/id/text()";
			String expression2 = "/leagues/league/current_round/text()";
			String expression3 = "/leagues/league/total_rounds/text()";
			String expression4 = "/leagues/league/name/text()";
			InputSource is = new InputSource(new StringReader(xml));
			NodeList listNames = (NodeList) xpath.evaluate(expression, is, XPathConstants.NODESET);
			
			InputSource is2 = new InputSource(new StringReader(xml));
			NodeList listNames2 = (NodeList) xpath.evaluate(expression2, is2, XPathConstants.NODESET);
			
			InputSource is3 = new InputSource(new StringReader(xml));
			NodeList listNames3 = (NodeList) xpath.evaluate(expression3, is3, XPathConstants.NODESET);
			
			InputSource is4 = new InputSource(new StringReader(xml));
			NodeList listNames4 = (NodeList) xpath.evaluate(expression4, is4, XPathConstants.NODESET);
			
			for(int i=0;i<listNames.getLength();i++){
				Node n =listNames.item(i);
				String value = n.getNodeValue();
				Node n2 =listNames2.item(i);
				String value2 = n2.getNodeValue();
				Node n3 =listNames3.item(i);
				String value3 = n3.getNodeValue();
				Node n4 =listNames4.item(i);
				String value4 = n4.getNodeValue();
				
				League l =new League();
				l.setId(Integer.parseInt(value));
				l.setCurrentRounds(Integer.parseInt(value2));
				l.setTotalRounds(Integer.parseInt(value3));
				l.setName(value4);
				list.add(l);
			
			}
			
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
