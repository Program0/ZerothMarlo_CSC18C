package edu.rcc.datastructures;

import java.util.Comparator;

public class Stuff implements Comparator<Stuff>, Comparable<Stuff> {
	private Integer numericValue;
	private String stringValue;
	private boolean alphabetValue;

	public Stuff() {
		numericValue = 0;
		stringValue = "a";
		alphabetValue = true;
	}

	public Stuff(int thing1, String thing2, boolean thing3) {
		this.numericValue = thing1;
		this.stringValue = thing2;
		this.alphabetValue = thing3;
	}

	public Integer getNumericalValue() {
		return numericValue;
	}

	public String getStringValue() {
		return stringValue;
	}

	public boolean getLetterValue() {
		return alphabetValue;
	}

	@Override
	public String toString() {
		String contents;
		contents = numericValue + " " + stringValue + " " + alphabetValue;
		return contents;
	}

	//Overriding the compare method
	@Override
	public int compare(Stuff stuff1, Stuff stuff2) {
		return stuff1.getNumericalValue().compareTo(stuff2.getNumericalValue());
	}
	
	//Overriding the compareTo method
	@Override
	public int compareTo(Stuff stuff1){
		return (this.numericValue.compareTo(stuff1.getNumericalValue()));
	}
}
