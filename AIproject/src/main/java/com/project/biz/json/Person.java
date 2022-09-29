package com.project.biz.json;

public class Person {
	private String Pname;
	private String person;
	
	public Person() {
		this.Pname = "";
		this.person = "";
	}
	
	public Person(String Pname, String person) {
		this.Pname = Pname;
		this.person = person;
	}
	
	public String getPname() {
		return Pname;
	}
	public void setPname(String pname) {
		Pname = pname;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
	
}
