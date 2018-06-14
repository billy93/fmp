package com.atibusinessgroup.fmp.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class RouteMapView {
	private String name;
	private List<RouteMapView> child = new ArrayList<>();
	private int rowSpan = 1;
	
	public RouteMapView() {
		// TODO Auto-generated constructor stub
	}
	
	public RouteMapView(String name) {
		this.name = name;
	}
	
	public RouteMapView(String name, List<RouteMapView> child) {
		this.name = name;
		this.child = child;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RouteMapView> getChild() {
		return child;
	}
	public void setChild(List<RouteMapView> child) {
		this.child = child;
	}
	public int getRowSpan() {
		return rowSpan;
	}
	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}

	@Override
	public String toString() {
		return "RouteMapView [name=" + name + ", rowSpan=" + rowSpan + ", child=" + child + "]";
	}

}
