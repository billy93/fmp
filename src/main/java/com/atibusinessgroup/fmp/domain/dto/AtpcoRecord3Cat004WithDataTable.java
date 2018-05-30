package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat04;

public class AtpcoRecord3Cat004WithDataTable {
	
	@Field("cat_04")
	private AtpcoRecord3Cat04 cat04;

	public AtpcoRecord3Cat04 getCat04() {
		return cat04;
	}

	public void setCat04(AtpcoRecord3Cat04 cat04) {
		this.cat04 = cat04;
	}

	@Override
	public String toString() {
		return "AtpcoRecord3Cat004WithDataTable [cat04=" + cat04 + "]";
	}
}
