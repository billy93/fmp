package com.atibusinessgroup.fmp.domain;

public class Group {
	private String name;

	public Group() {
		this.name = null;
	}
	
	public Group(String name) {
		this.name = name.toUpperCase();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Group [name=" + name + "]";
	}
}
