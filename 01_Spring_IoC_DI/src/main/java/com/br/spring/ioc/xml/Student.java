package com.br.spring.ioc.xml;

public class Student {

	private String naeme;
	private String academy;
	private String classColor;
	private Calculator calc;
	
	public Student() {}

	public Student(String naeme, String academy, String classColor, Calculator calc) {
		super();
		this.naeme = naeme;
		this.academy = academy;
		this.classColor = classColor;
		this.calc = calc;
	}

	public String getNaeme() {
		return naeme;
	}

	public void setNaeme(String naeme) {
		this.naeme = naeme;
	}

	public String getAcademy() {
		return academy;
	}

	public void setAcademy(String academy) {
		this.academy = academy;
	}

	public String getClassColor() {
		return classColor;
	}

	public void setClassColor(String classColor) {
		this.classColor = classColor;
	}

	public Calculator getCalc() {
		return calc;
	}

	public void setCalc(Calculator calc) {
		this.calc = calc;
	}

	@Override
	public String toString() {
		return "Student [naeme=" + naeme + ", academy=" + academy + ", classColor=" + classColor + ", calc=" + calc
				+ "]";
	}
	
	
	
}
