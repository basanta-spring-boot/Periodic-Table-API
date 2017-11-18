package com.intermediate.periodic.table.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.intermediate.periodic.table.api.dto.PeriodicTableResponse;

@Service
public class PeriodicTableService {

	private List<PeriodicTableResponse> responses;

	private List<String> elements;

	public List<String> getElements() {
		return elements;
	}

	public void setElements(List<String> elements) {
		this.elements = elements;
	}

	public List<PeriodicTableResponse> getResponses() {
		return responses;
	}

	public void setResponses(List<PeriodicTableResponse> responses) {
		this.responses = responses;
	}

	public PeriodicTableResponse getAtomicResponse(String element) {
		return responses.stream().filter(obj -> obj.getElementName().equalsIgnoreCase(element)).findAny().get();
	}
}
