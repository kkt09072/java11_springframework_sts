package com.spring1.dto;

import java.util.Set;

public class Mart {
	private String martName;
	private Set<Fruits> frSet;
	public Mart() { }
	public Mart(String martName, Set<Fruits> frSet) {
		super();
		this.martName = martName;
		this.frSet = frSet;
	}
	public String getMartName() {
		return martName;
	}
	public void setMartName(String martName) {
		this.martName = martName;
	}
	public Set<Fruits> getFrSet() {
		return frSet;
	}
	public void setFrSet(Set<Fruits> frSet) {
		this.frSet = frSet;
	}
	@Override
	public String toString() {
		return "Mart [martName=" + martName + ", frSet=" + frSet + "]";
	}
	
}
