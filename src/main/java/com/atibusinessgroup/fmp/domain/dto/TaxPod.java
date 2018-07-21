package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class TaxPod {

	@Field("pod_geo_type")
	private String pod_geo_type;

	@Field("pod_geo_value")
    private String pod_geo_value;

	public String getPod_geo_type() {
		return pod_geo_type;
	}

	public void setPod_geo_type(String pod_geo_type) {
		this.pod_geo_type = pod_geo_type;
	}

	public String getPod_geo_value() {
		return pod_geo_value;
	}

	public void setPod_geo_value(String pod_geo_value) {
		this.pod_geo_value = pod_geo_value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pod_geo_type == null) ? 0 : pod_geo_type.hashCode());
		result = prime * result + ((pod_geo_value == null) ? 0 : pod_geo_value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TaxPod other = (TaxPod) obj;
		if (pod_geo_type == null) {
			if (other.pod_geo_type != null) {
				return false;
			}
		} else if (!pod_geo_type.equals(other.pod_geo_type)) {
			return false;
		}
		if (pod_geo_value == null) {
			if (other.pod_geo_value != null) {
				return false;
			}
		} else if (!pod_geo_value.equals(other.pod_geo_value)) {
			return false;
		}
		return true;
	}
}
