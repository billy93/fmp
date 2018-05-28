package com.atibusinessgroup.fmp.domain.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public class AtpcoFareChangeTags {
	
	@Field("change_tags_1")
	private String tag1NewRecord;
	
	@Field("change_tags_2")
	private String tag2IncreasedFare;
	
	@Field("change_tags_3")
	private String tag3ReducedFare;
	
	@Field("change_tags_4")
	private String tag4CurrentlyNotBeingUsed;
	
	@Field("change_tags_5")
	private String tag5FootnoteChange;
	
	@Field("change_tags_6")
	private String tag6RoutingMapChange;
	
	@Field("change_tags_7")
	private String tag7ReservedForFutureUse;
	
	@Field("change_tags_8")
	private String tag8MaximumPermittedMileageChange;
	
	@Field("change_tags_9")
	private String tag9ReservedForFutureUse;
	
	@Field("change_tags_10")
	private String tag10EffectiveDateChange;
	
	@Field("change_tags_11")
	private String tag11ReinstatementFare;
	
	@Field("change_tags_12")
	private String tag12Reissue;
	
	@Field("change_tags_13")
	private String tag13ReservedForFutureUse;
	
	@Field("change_tags_14")
	private String tag14NoChange;
	
	@Field("change_tags_15")
	private String tag15CancelledRecord;
	
	@Field("change_tags_16")
	private String tag16NoComponentChange;
	
	@Field("change_tags_17")
	private String tag17CurrencyCodeChange;
	
	@Field("change_tags_18")
	private String tag18FareNotSubjectToNucConversion;

	public String getTag1NewRecord() {
		return tag1NewRecord;
	}

	public void setTag1NewRecord(String tag1NewRecord) {
		this.tag1NewRecord = tag1NewRecord;
	}

	public String getTag2IncreasedFare() {
		return tag2IncreasedFare;
	}

	public void setTag2IncreasedFare(String tag2IncreasedFare) {
		this.tag2IncreasedFare = tag2IncreasedFare;
	}

	public String getTag3ReducedFare() {
		return tag3ReducedFare;
	}

	public void setTag3ReducedFare(String tag3ReducedFare) {
		this.tag3ReducedFare = tag3ReducedFare;
	}

	public String getTag4CurrentlyNotBeingUsed() {
		return tag4CurrentlyNotBeingUsed;
	}

	public void setTag4CurrentlyNotBeingUsed(String tag4CurrentlyNotBeingUsed) {
		this.tag4CurrentlyNotBeingUsed = tag4CurrentlyNotBeingUsed;
	}

	public String getTag5FootnoteChange() {
		return tag5FootnoteChange;
	}

	public void setTag5FootnoteChange(String tag5FootnoteChange) {
		this.tag5FootnoteChange = tag5FootnoteChange;
	}

	public String getTag6RoutingMapChange() {
		return tag6RoutingMapChange;
	}

	public void setTag6RoutingMapChange(String tag6RoutingMapChange) {
		this.tag6RoutingMapChange = tag6RoutingMapChange;
	}

	public String getTag7ReservedForFutureUse() {
		return tag7ReservedForFutureUse;
	}

	public void setTag7ReservedForFutureUse(String tag7ReservedForFutureUse) {
		this.tag7ReservedForFutureUse = tag7ReservedForFutureUse;
	}

	public String getTag8MaximumPermittedMileageChange() {
		return tag8MaximumPermittedMileageChange;
	}

	public void setTag8MaximumPermittedMileageChange(String tag8MaximumPermittedMileageChange) {
		this.tag8MaximumPermittedMileageChange = tag8MaximumPermittedMileageChange;
	}

	public String getTag9ReservedForFutureUse() {
		return tag9ReservedForFutureUse;
	}

	public void setTag9ReservedForFutureUse(String tag9ReservedForFutureUse) {
		this.tag9ReservedForFutureUse = tag9ReservedForFutureUse;
	}

	public String getTag10EffectiveDateChange() {
		return tag10EffectiveDateChange;
	}

	public void setTag10EffectiveDateChange(String tag10EffectiveDateChange) {
		this.tag10EffectiveDateChange = tag10EffectiveDateChange;
	}

	public String getTag11ReinstatementFare() {
		return tag11ReinstatementFare;
	}

	public void setTag11ReinstatementFare(String tag11ReinstatementFare) {
		this.tag11ReinstatementFare = tag11ReinstatementFare;
	}

	public String getTag12Reissue() {
		return tag12Reissue;
	}

	public void setTag12Reissue(String tag12Reissue) {
		this.tag12Reissue = tag12Reissue;
	}

	public String getTag13ReservedForFutureUse() {
		return tag13ReservedForFutureUse;
	}

	public void setTag13ReservedForFutureUse(String tag13ReservedForFutureUse) {
		this.tag13ReservedForFutureUse = tag13ReservedForFutureUse;
	}

	public String getTag14NoChange() {
		return tag14NoChange;
	}

	public void setTag14NoChange(String tag14NoChange) {
		this.tag14NoChange = tag14NoChange;
	}

	public String getTag15CancelledRecord() {
		return tag15CancelledRecord;
	}

	public void setTag15CancelledRecord(String tag15CancelledRecord) {
		this.tag15CancelledRecord = tag15CancelledRecord;
	}

	public String getTag16NoComponentChange() {
		return tag16NoComponentChange;
	}

	public void setTag16NoComponentChange(String tag16NoComponentChange) {
		this.tag16NoComponentChange = tag16NoComponentChange;
	}

	public String getTag17CurrencyCodeChange() {
		return tag17CurrencyCodeChange;
	}

	public void setTag17CurrencyCodeChange(String tag17CurrencyCodeChange) {
		this.tag17CurrencyCodeChange = tag17CurrencyCodeChange;
	}

	public String getTag18FareNotSubjectToNucConversion() {
		return tag18FareNotSubjectToNucConversion;
	}

	public void setTag18FareNotSubjectToNucConversion(String tag18FareNotSubjectToNucConversion) {
		this.tag18FareNotSubjectToNucConversion = tag18FareNotSubjectToNucConversion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tag10EffectiveDateChange == null) ? 0 : tag10EffectiveDateChange.hashCode());
		result = prime * result + ((tag11ReinstatementFare == null) ? 0 : tag11ReinstatementFare.hashCode());
		result = prime * result + ((tag12Reissue == null) ? 0 : tag12Reissue.hashCode());
		result = prime * result + ((tag13ReservedForFutureUse == null) ? 0 : tag13ReservedForFutureUse.hashCode());
		result = prime * result + ((tag14NoChange == null) ? 0 : tag14NoChange.hashCode());
		result = prime * result + ((tag15CancelledRecord == null) ? 0 : tag15CancelledRecord.hashCode());
		result = prime * result + ((tag16NoComponentChange == null) ? 0 : tag16NoComponentChange.hashCode());
		result = prime * result + ((tag17CurrencyCodeChange == null) ? 0 : tag17CurrencyCodeChange.hashCode());
		result = prime * result
				+ ((tag18FareNotSubjectToNucConversion == null) ? 0 : tag18FareNotSubjectToNucConversion.hashCode());
		result = prime * result + ((tag1NewRecord == null) ? 0 : tag1NewRecord.hashCode());
		result = prime * result + ((tag2IncreasedFare == null) ? 0 : tag2IncreasedFare.hashCode());
		result = prime * result + ((tag3ReducedFare == null) ? 0 : tag3ReducedFare.hashCode());
		result = prime * result + ((tag4CurrentlyNotBeingUsed == null) ? 0 : tag4CurrentlyNotBeingUsed.hashCode());
		result = prime * result + ((tag5FootnoteChange == null) ? 0 : tag5FootnoteChange.hashCode());
		result = prime * result + ((tag6RoutingMapChange == null) ? 0 : tag6RoutingMapChange.hashCode());
		result = prime * result + ((tag7ReservedForFutureUse == null) ? 0 : tag7ReservedForFutureUse.hashCode());
		result = prime * result
				+ ((tag8MaximumPermittedMileageChange == null) ? 0 : tag8MaximumPermittedMileageChange.hashCode());
		result = prime * result + ((tag9ReservedForFutureUse == null) ? 0 : tag9ReservedForFutureUse.hashCode());
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
		AtpcoFareChangeTags other = (AtpcoFareChangeTags) obj;
		if (tag10EffectiveDateChange == null) {
			if (other.tag10EffectiveDateChange != null) {
				return false;
			}
		} else if (!tag10EffectiveDateChange.equals(other.tag10EffectiveDateChange)) {
			return false;
		}
		if (tag11ReinstatementFare == null) {
			if (other.tag11ReinstatementFare != null) {
				return false;
			}
		} else if (!tag11ReinstatementFare.equals(other.tag11ReinstatementFare)) {
			return false;
		}
		if (tag12Reissue == null) {
			if (other.tag12Reissue != null) {
				return false;
			}
		} else if (!tag12Reissue.equals(other.tag12Reissue)) {
			return false;
		}
		if (tag13ReservedForFutureUse == null) {
			if (other.tag13ReservedForFutureUse != null) {
				return false;
			}
		} else if (!tag13ReservedForFutureUse.equals(other.tag13ReservedForFutureUse)) {
			return false;
		}
		if (tag14NoChange == null) {
			if (other.tag14NoChange != null) {
				return false;
			}
		} else if (!tag14NoChange.equals(other.tag14NoChange)) {
			return false;
		}
		if (tag15CancelledRecord == null) {
			if (other.tag15CancelledRecord != null) {
				return false;
			}
		} else if (!tag15CancelledRecord.equals(other.tag15CancelledRecord)) {
			return false;
		}
		if (tag16NoComponentChange == null) {
			if (other.tag16NoComponentChange != null) {
				return false;
			}
		} else if (!tag16NoComponentChange.equals(other.tag16NoComponentChange)) {
			return false;
		}
		if (tag17CurrencyCodeChange == null) {
			if (other.tag17CurrencyCodeChange != null) {
				return false;
			}
		} else if (!tag17CurrencyCodeChange.equals(other.tag17CurrencyCodeChange)) {
			return false;
		}
		if (tag18FareNotSubjectToNucConversion == null) {
			if (other.tag18FareNotSubjectToNucConversion != null) {
				return false;
			}
		} else if (!tag18FareNotSubjectToNucConversion.equals(other.tag18FareNotSubjectToNucConversion)) {
			return false;
		}
		if (tag1NewRecord == null) {
			if (other.tag1NewRecord != null) {
				return false;
			}
		} else if (!tag1NewRecord.equals(other.tag1NewRecord)) {
			return false;
		}
		if (tag2IncreasedFare == null) {
			if (other.tag2IncreasedFare != null) {
				return false;
			}
		} else if (!tag2IncreasedFare.equals(other.tag2IncreasedFare)) {
			return false;
		}
		if (tag3ReducedFare == null) {
			if (other.tag3ReducedFare != null) {
				return false;
			}
		} else if (!tag3ReducedFare.equals(other.tag3ReducedFare)) {
			return false;
		}
		if (tag4CurrentlyNotBeingUsed == null) {
			if (other.tag4CurrentlyNotBeingUsed != null) {
				return false;
			}
		} else if (!tag4CurrentlyNotBeingUsed.equals(other.tag4CurrentlyNotBeingUsed)) {
			return false;
		}
		if (tag5FootnoteChange == null) {
			if (other.tag5FootnoteChange != null) {
				return false;
			}
		} else if (!tag5FootnoteChange.equals(other.tag5FootnoteChange)) {
			return false;
		}
		if (tag6RoutingMapChange == null) {
			if (other.tag6RoutingMapChange != null) {
				return false;
			}
		} else if (!tag6RoutingMapChange.equals(other.tag6RoutingMapChange)) {
			return false;
		}
		if (tag7ReservedForFutureUse == null) {
			if (other.tag7ReservedForFutureUse != null) {
				return false;
			}
		} else if (!tag7ReservedForFutureUse.equals(other.tag7ReservedForFutureUse)) {
			return false;
		}
		if (tag8MaximumPermittedMileageChange == null) {
			if (other.tag8MaximumPermittedMileageChange != null) {
				return false;
			}
		} else if (!tag8MaximumPermittedMileageChange.equals(other.tag8MaximumPermittedMileageChange)) {
			return false;
		}
		if (tag9ReservedForFutureUse == null) {
			if (other.tag9ReservedForFutureUse != null) {
				return false;
			}
		} else if (!tag9ReservedForFutureUse.equals(other.tag9ReservedForFutureUse)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "AtpcoFareChangeTags [tag1NewRecord=" + tag1NewRecord + ", tag2IncreasedFare=" + tag2IncreasedFare
				+ ", tag3ReducedFare=" + tag3ReducedFare + ", tag4CurrentlyNotBeingUsed=" + tag4CurrentlyNotBeingUsed
				+ ", tag5FootnoteChange=" + tag5FootnoteChange + ", tag6RoutingMapChange=" + tag6RoutingMapChange
				+ ", tag7ReservedForFutureUse=" + tag7ReservedForFutureUse + ", tag8MaximumPermittedMileageChange="
				+ tag8MaximumPermittedMileageChange + ", tag9ReservedForFutureUse=" + tag9ReservedForFutureUse
				+ ", tag10EffectiveDateChange=" + tag10EffectiveDateChange + ", tag11ReinstatementFare="
				+ tag11ReinstatementFare + ", tag12Reissue=" + tag12Reissue + ", tag13ReservedForFutureUse="
				+ tag13ReservedForFutureUse + ", tag14NoChange=" + tag14NoChange + ", tag15CancelledRecord="
				+ tag15CancelledRecord + ", tag16NoComponentChange=" + tag16NoComponentChange
				+ ", tag17CurrencyCodeChange=" + tag17CurrencyCodeChange + ", tag18FareNotSubjectToNucConversion="
				+ tag18FareNotSubjectToNucConversion + "]";
	}
}
