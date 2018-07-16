package com.atibusinessgroup.fmp.domain.dto;

public class InternetQueryExportParam {
	
	private InternetQueryParam internetQueryParam;
	private String outputTo;
	private boolean gridLines;
	private boolean columnHeaders;
	private boolean onlySelectedRows;
	
	public InternetQueryParam getInternetQueryParam() {
		return internetQueryParam;
	}
	public void setInternetQueryParam(InternetQueryParam internetQueryParam) {
		this.internetQueryParam = internetQueryParam;
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
	
	@Override
	public String toString() {
		return "InternetQueryExportParam [internetQueryParam=" + internetQueryParam + ", outputTo=" + outputTo
				+ ", gridLines=" + gridLines + ", columnHeaders=" + columnHeaders + ", onlySelectedRows="
				+ onlySelectedRows + "]";
	}

	
}
