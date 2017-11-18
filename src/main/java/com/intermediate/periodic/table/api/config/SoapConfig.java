package com.intermediate.periodic.table.api.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.intermediate.periodic.table.api.schemas.GetAtomicNumber;
import com.intermediate.periodic.table.api.schemas.GetAtomicNumberResponse;
import com.intermediate.periodic.table.api.schemas.GetAtoms;
import com.intermediate.periodic.table.api.schemas.GetAtomsResponse;

@SpringBootApplication
public class SoapConfig {
	@Bean(name = "marshaller1")
	public Jaxb2Marshaller marshaller1() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(GetAtoms.class, GetAtomsResponse.class);
		return marshaller;
	}

	@Bean(name = "marshaller2")
	public Jaxb2Marshaller marshaller2() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(GetAtomicNumber.class, GetAtomicNumberResponse.class);
		return marshaller;
	}
}
