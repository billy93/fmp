package com.atibusinessgroup.fmp.domain.dto;

import com.atibusinessgroup.fmp.domain.RoutingQuery;

public class RoutingQueryExportParam {

	private RoutingQuery routingQuery;
	private String outputTo;
	private boolean gridLines;
	private boolean columnHeaders;
	private boolean onlySelectedRows;
	private int tab;
	
	public RoutingQuery getRoutingQuery() {
		return routingQuery;
	}
	public void setRoutingQuery(RoutingQuery routingQuery) {
		this.routingQuery = routingQuery;
	}
	public String getOutputTo() {
		return outputTo;
	}
	public void setOutputTo(String outputTo) {
		this.outputTo = outputTo;
	}
	public boolean isGridLines() {
		return gridLines;
	}
	public void setGridLines(boolean gridLines) {
		this.gridLines = gridLines;
	}
	public boolean isColumnHeaders() {
		return columnHeaders;
	}
	public void setColumnHeaders(boolean columnHeaders) {
		this.columnHeaders = columnHeaders;
	}
	public boolean isOnlySelectedRows() {
		return onlySelectedRows;
	}
	public void setOnlySelectedRows(boolean onlySelectedRows) {
		this.onlySelectedRows = onlySelectedRows;
	}
	public int getTab() {
		return tab;
	}
	public void setTab(int tab) {
		this.tab = tab;
	}
	
	@Override
	public String toString() {
		return "RoutingQueryExportParam [routingQuery=" + routingQuery + ", outputTo=" + outputTo + ", gridLines="
				+ gridLines + ", columnHeaders=" + columnHeaders + ", onlySelectedRows=" + onlySelectedRows + ", tab="
				+ tab + "]";
	}
	
}
