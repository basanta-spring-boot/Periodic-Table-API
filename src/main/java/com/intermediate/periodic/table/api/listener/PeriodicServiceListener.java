package com.intermediate.periodic.table.api.listener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import com.intermediate.periodic.table.api.client.PeriodicTableClient;
import com.intermediate.periodic.table.api.dto.PeriodicTableResponse;
import com.intermediate.periodic.table.api.service.PeriodicTableService;

@Component
public class PeriodicServiceListener implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private PeriodicTableService service;
	@Autowired
	private PeriodicTableClient client;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		List<PeriodicTableResponse> responses = new ArrayList<>();
		List<String> elements = null;
		try {
			elements = client.parseAtoms();
			elements.stream().forEach(element -> {
				try {
					responses.add(client.getAtomicInfo(element));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			});
			service.setElements(elements);
			// sorted Response based on atomic number
			service.setResponses(responses.stream().sorted(Comparator.comparing(PeriodicTableResponse::getAtomicNumber))
					.collect(Collectors.toList()));

		} catch (XPathExpressionException | SAXException | IOException | ParserConfigurationException e) {
			System.out.println(e.getMessage());
		}

	}

}
