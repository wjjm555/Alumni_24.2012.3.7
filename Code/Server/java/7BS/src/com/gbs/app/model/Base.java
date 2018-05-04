package com.gbs.app.model;

public class Base {
	private String describe;
	private String name;

	public String getName() {
		return name;
	}

	public Base setName(String name) {
		this.name = name;
		return this;
	}

	public String getDescribe() {
		return describe;
	}

	public Base setDescribe(String describe) {
		this.describe = describe;
		return this;
	}
}
