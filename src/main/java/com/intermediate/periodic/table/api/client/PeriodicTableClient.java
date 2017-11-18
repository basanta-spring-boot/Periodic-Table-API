package com.intermediate.periodic.table.api.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;
import org.xml.sax.SAXException;

import com.intermediate.periodic.table.api.dto.PeriodicTableResponse;
import com.intermediate.periodic.table.api.schemas.GetAtomicNumber;
import com.intermediate.periodic.table.api.schemas.GetAtomicNumberResponse;
import com.intermediate.periodic.table.api.schemas.GetAtoms;
import com.intermediate.periodic.table.api.schemas.GetAtomsResponse;
import com.intermediate.periodic.table.api.util.ParseUtil;

@Service
@PropertySource(value = { "application.properties" })
public class PeriodicTableClient {
	@Autowired
	@Qualifier("marshaller1")
	private Jaxb2Marshaller marshaller1;

	@Autowired
	@Qualifier("marshaller2")
	private Jaxb2Marshaller marshaller2;

	@Value("${periodictable.endpoint.url}")
	private String END_POINT_URL;

	@Value("${periodic.table.getAtoms.soapAction}")
	private String GET_ATOMS_SOAP_ACTION;

	@Value("${periodic.table.getAtomicInformation.soapAction}")
	private String GET_ATOMIC_INFORMATION_SOAP_ACTION;

	@Autowired
	private ParseUtil util;

	private WebServiceTemplate template;

	public String getAtoms() {
		String URI = END_POINT_URL;
		GetAtoms atoms = new GetAtoms();
		template = new WebServiceTemplate(marshaller1);
		GetAtomsResponse response = (GetAtomsResponse) template.marshalSendAndReceive(URI, atoms,
				new WebServiceMessageCallback() {
					@Override
					public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
						((SoapMessage) message).setSoapAction(GET_ATOMS_SOAP_ACTION);

					}
				});
		return response.getGetAtomsResult();
	}

	public String getAtomInformation(String element) {
		String URL = END_POINT_URL;
		GetAtomicNumber atomicNumber = new GetAtomicNumber();
		atomicNumber.setElementName(element);
		template = new WebServiceTemplate(marshaller2);
		GetAtomicNumberResponse response = (GetAtomicNumberResponse) template.marshalSendAndReceive(URL, atomicNumber,
				new WebServiceMessageCallback() {
					@Override
					public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
						((SoapMessage) message).setSoapAction(GET_ATOMIC_INFORMATION_SOAP_ACTION);

					}
				});
		return response.getGetAtomicNumberResult();

	}

	public List<String> parseAtoms()
			throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
		return util.getAtomsName(getAtoms());
	}

	public PeriodicTableResponse getAtomicInfo(String element) throws Exception {
		return util.getAtomicNumberResponse(getAtomInformation(element));
	}

	public List<PeriodicTableResponse> getFullPeriodicTable(List<String> elements) {
		List<PeriodicTableResponse> responses = new ArrayList<>();
		elements.stream().forEach(element -> {
			try {
				responses.add(getAtomicInfo(element));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
		return responses;
	}
}
