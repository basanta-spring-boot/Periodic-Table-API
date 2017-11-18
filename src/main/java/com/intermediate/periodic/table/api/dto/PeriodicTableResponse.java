package com.intermediate.periodic.table.api.dto;

import java.util.List;

public class PeriodicTableResponse {

	private List<String> list = null;

	private int atomicNumber;
	private String elementName;
	private String symbol;
	private String atomicWeight;
	private String boilingPoint;
	private String ionisationPotential;
	private String eletroNegativity;
	private String atomicRadius;
	private String meltingPoint;
	private String density;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	

	public int getAtomicNumber() {
		return atomicNumber;
	}

	public void setAtomicNumber(int atomicNumber) {
		this.atomicNumber = atomicNumber;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getAtomicWeight() {
		return atomicWeight;
	}

	public void setAtomicWeight(String atomicWeight) {
		this.atomicWeight = atomicWeight;
	}

	public String getBoilingPoint() {
		return boilingPoint;
	}

	public void setBoilingPoint(String boilingPoint) {
		this.boilingPoint = boilingPoint;
	}

	public String getIonisationPotential() {
		return ionisationPotential;
	}

	public void setIonisationPotential(String ionisationPotential) {
		this.ionisationPotential = ionisationPotential;
	}

	public String getEletroNegativity() {
		return eletroNegativity;
	}

	public void setEletroNegativity(String eletroNegativity) {
		this.eletroNegativity = eletroNegativity;
	}

	public String getAtomicRadius() {
		return atomicRadius;
	}

	public void setAtomicRadius(String atomicRadius) {
		this.atomicRadius = atomicRadius;
	}

	public String getMeltingPoint() {
		return meltingPoint;
	}

	public void setMeltingPoint(String meltingPoint) {
		this.meltingPoint = meltingPoint;
	}

	public String getDensity() {
		return density;
	}

	public void setDensity(String density) {
		this.density = density;
	}

	public PeriodicTableResponse(int atomicNumber, String elementName, String symbol, String atomicWeight,
			String boilingPoint, String ionisationPotential, String eletroNegativity, String atomicRadius,
			String meltingPoint, String density) {
		super();
		this.atomicNumber = atomicNumber;
		this.elementName = elementName;
		this.symbol = symbol;
		this.atomicWeight = atomicWeight;
		this.boilingPoint = boilingPoint;
		this.ionisationPotential = ionisationPotential;
		this.eletroNegativity = eletroNegativity;
		this.atomicRadius = atomicRadius;
		this.meltingPoint = meltingPoint;
		this.density = density;
	}

	public PeriodicTableResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

}
