import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Score extends Extractor {
	private String localName;
	private String visitorName;
	private int localGoals;
	private int visitorsGoals;

	public Score() {
		super();
	}

	public Score(String localName, String visitorName, int localGoals, int visitorsGoals) {
		super();
		this.localName = localName;
		this.visitorName = visitorName;
		this.localGoals = localGoals;
		this.visitorsGoals = visitorsGoals;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public int getLocalGoals() {
		return localGoals;
	}

	public void setLocalGoals(int localGoals) {
		this.localGoals = localGoals;
	}

	public int getVisitorsGoals() {
		return visitorsGoals;
	}

	public void setVisitorsGoals(int visitorsGoals) {
		this.visitorsGoals = visitorsGoals;
	}

	@Override
	public String toString() {
		return localName + " " + localGoals + " - " + visitorsGoals + visitorName ;
	}

	@Override
	public ArrayList<Score> extract(String xml) {
		ArrayList<Score> list = new ArrayList<Score>();
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			String local = "/matchs/match/local/text()";
			String visit = "/matchs/match/visitor/text()";
			String local_goal = "/matchs/match/local_goals/text()";
			String visit_goal = "/matchs/match/visitor_goals/text()";

			InputSource is = new InputSource(new StringReader(xml));
			NodeList listLocal = (NodeList) xpath.evaluate(local, is, XPathConstants.NODESET);
			InputSource is2 = new InputSource(new StringReader(xml));
			NodeList listGoalL = (NodeList) xpath.evaluate(local_goal, is2, XPathConstants.NODESET);
			InputSource is3 = new InputSource(new StringReader(xml));
			NodeList listGoalV = (NodeList) xpath.evaluate(visit_goal, is3, XPathConstants.NODESET);
			InputSource is4 = new InputSource(new StringReader(xml));
			NodeList listVisit = (NodeList) xpath.evaluate(visit, is4, XPathConstants.NODESET);

			for (int i = 0; i < listLocal.getLength(); i++) {
				Node n = listLocal.item(i);
				String l = n.getNodeValue();
				Node n2 = listGoalL.item(i);
				String lg = n2.getNodeValue();
				Node n3 = listGoalV.item(i);
				String vg = n3.getNodeValue();
				Node n4 = listVisit.item(i);
				String v = n4.getNodeValue();

				// System.out.println(" "+l+" "+lg+" - "+vg+" "+v);

				Score s = new Score();
				s.setLocalName(l);
				s.setVisitorName(v);
				if (lg.equals("x")) {
					s.setLocalGoals(-1);
				} else {
					s.setLocalGoals(Integer.parseInt(lg));
				}
				if (vg.equals("x")) {
					s.setVisitorsGoals(-1);
				} else {
					s.setVisitorsGoals(Integer.parseInt(vg));
				}
				list.add(s);
			}

		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;

	}
}
