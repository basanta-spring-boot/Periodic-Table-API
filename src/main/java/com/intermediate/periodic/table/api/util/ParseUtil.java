package com.intermediate.periodic.table.api.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.intermediate.periodic.table.api.dto.PeriodicTableResponse;

@Component
public class ParseUtil {

	public List<String> getAtomsName(String xml)
			throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		List<String> atomsName = new ArrayList<>();
		NodeList nodeList = getNodeList(xml, "/NewDataSet/Table/ElementName");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node.getChildNodes() != null)
				atomsName.add(i, node.getTextContent());

		}
		return atomsName;
	}

	private NodeList getNodeList(String xml, String Expression)
			throws SAXException, IOException, ParserConfigurationException, XPathExpressionException {
		// Turn String into a Document
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new ByteArrayInputStream(xml.getBytes()));
		// Setup XPath to retrieve all tags and values
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList nodeList = (NodeList) xPath.evaluate(Expression, document, XPathConstants.NODESET);
		return nodeList;
	}

	public PeriodicTableResponse getAtomicNumberResponse(String xml) throws Exception {
		PeriodicTableResponse response = null;
		InputSource source = new InputSource(new StringReader(xml));
		XPath xpath = XPathFactory.newInstance().newXPath();
		Object customer = xpath.evaluate("/NewDataSet/Table", source, XPathConstants.NODE);
		// get all fields one by one by passing the element name
		String atomicNumber = xpath.evaluate("AtomicNumber", customer);
		String elementName = xpath.evaluate("ElementName", customer);
		String symbol = xpath.evaluate("Symbol", customer);
		String atomicWeight = xpath.evaluate("AtomicWeight", customer);
		String boilingPoint = xpath.evaluate("BoilingPoint", customer);
		String ionisationPotential = xpath.evaluate("IonisationPotential", customer);
		String eletroNegativity = xpath.evaluate("EletroNegativity", customer);
		String atomicRadius = xpath.evaluate("AtomicRadius", customer);
		String meltingPoint = xpath.evaluate("MeltingPoint", customer);
		String density = xpath.evaluate("Density", customer);
		// minimize decimal number to format table properly
		if (atomicWeight.length() > 5) {
			atomicWeight = atomicWeight.substring(0, 5);
		}
		if (boilingPoint.length() > 5) {
			boilingPoint = boilingPoint.substring(0, 5);
		}
		if (ionisationPotential.length() > 5) {
			ionisationPotential = ionisationPotential.substring(0, 5);
		}
		if (eletroNegativity.length() > 5) {
			eletroNegativity = eletroNegativity.substring(0, 5);
		}
		if (atomicRadius.length() > 5) {
			atomicRadius = atomicRadius.substring(0, 5);
		}
		response = new PeriodicTableResponse(Integer.valueOf(atomicNumber), elementName, symbol, atomicWeight,
				boilingPoint, ionisationPotential, eletroNegativity, atomicRadius, meltingPoint, density);
		return response;

	}
}
