package com.intermediate.periodic.table.api.exception.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class PeriodicTableExceptionHandler {
	@ExceptionHandler(PeriodictableServiceException.class)
	public ModelAndView handelException(PeriodictableServiceException ex) {
		ModelAndView mav = new ModelAndView("serviceError");
		mav.addObject("errorMessage", ex.getMessage());
		return mav;
	}
}
