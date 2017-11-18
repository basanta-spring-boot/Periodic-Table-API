package com.intermediate.periodic.table.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intermediate.periodic.table.api.dto.PeriodicTableResponse;
import com.intermediate.periodic.table.api.exception.advice.PeriodictableServiceException;
import com.intermediate.periodic.table.api.service.PeriodicTableService;

@Controller
@RequestMapping("/periodicTable")
public class PeriodicTableController {

	private List<String> elementList = null;
	@Autowired
	private PeriodicTableService service;

	@RequestMapping("/get")
	public String getAtoms(Model model) {
		PeriodicTableResponse response = new PeriodicTableResponse();
		List<String> list = service.getElements();
		response.setList(list);
		elementList = list;
		model.addAttribute("response", response);
		model.addAttribute("size", list.size());
		return "home";
	}

	@RequestMapping("/getAtomInfo")
	public String getAtomdetails(Model model, @RequestParam("element") String element)
			throws PeriodictableServiceException {
		if (element.equalsIgnoreCase("error")) {
			throw new PeriodictableServiceException(
					"You must have to select atleast one atom to get the periodic information !!");
		}
		PeriodicTableResponse response = service.getAtomicResponse(element);
		response.setList(elementList);
		model.addAttribute("response", response);
		List<String> dummylist = new ArrayList<>();
		dummylist.add("");
		model.addAttribute("dummylist", dummylist);
		model.addAttribute("size", elementList.size());
		return "home";
	}

	@GetMapping("/viewFullTable")
	public String viewPeriodicTable(Model model) {
		List<PeriodicTableResponse> responses = null;
		responses = service.getResponses();
		model.addAttribute("responses", responses);
		return "periodicTable";
	}
}
