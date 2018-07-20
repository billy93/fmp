package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A FBR Report.
 */
@Document(collection = "atpco_fbr_report")
public class FbrReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("creator")
    private String creator;

    @Field("carrier")
    private String carrier;
    
    @Field("tariff")
    private String tariff;
    
    @Field("rule_no")
    private String ruleNo;
    
    @Field("rule_title")
    private String ruleTitle;
    
    @Field("report_name")
    private String reportName;
    
    @Field("fare_count")
    private int fareCount;
    
    @Field("file_path")
    private String filepath;
    
    @Field("progress")
    private int progress;
    
    @Field("error")
    private boolean error;
    
    @Field("error_message")
    private String errorMessage;
    
    @Field("created_date")
    private Instant createdDate;

    private byte[] file;
    
    private String filetype;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getTariff() {
		return tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public String getRuleNo() {
		return ruleNo;
	}

	public void setRuleNo(String ruleNo) {
		this.ruleNo = ruleNo;
	}

	public String getRuleTitle() {
		return ruleTitle;
	}

	public void setRuleTitle(String ruleTitle) {
		this.ruleTitle = ruleTitle;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public int getFareCount() {
		return fareCount;
	}

	public void setFareCount(int fareCount) {
		this.fareCount = fareCount;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Instant getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Instant createdDate) {
		this.createdDate = createdDate;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carrier == null) ? 0 : carrier.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + (error ? 1231 : 1237);
		result = prime * result + ((errorMessage == null) ? 0 : errorMessage.hashCode());
		result = prime * result + fareCount;
		result = prime * result + Arrays.hashCode(file);
		result = prime * result + ((filepath == null) ? 0 : filepath.hashCode());
		result = prime * result + ((filetype == null) ? 0 : filetype.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + progress;
		result = prime * result + ((reportName == null) ? 0 : reportName.hashCode());
		result = prime * result + ((ruleNo == null) ? 0 : ruleNo.hashCode());
		result = prime * result + ((ruleTitle == null) ? 0 : ruleTitle.hashCode());
		result = prime * result + ((tariff == null) ? 0 : tariff.hashCode());
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
		FbrReport other = (FbrReport) obj;
		if (carrier == null) {
			if (other.carrier != null) {
				return false;
			}
		} else if (!carrier.equals(other.carrier)) {
			return false;
		}
		if (createdDate == null) {
			if (other.createdDate != null) {
				return false;
			}
		} else if (!createdDate.equals(other.createdDate)) {
			return false;
		}
		if (creator == null) {
			if (other.creator != null) {
				return false;
			}
		} else if (!creator.equals(other.creator)) {
			return false;
		}
		if (error != other.error) {
			return false;
		}
		if (errorMessage == null) {
			if (other.errorMessage != null) {
				return false;
			}
		} else if (!errorMessage.equals(other.errorMessage)) {
			return false;
		}
		if (fareCount != other.fareCount) {
			return false;
		}
		if (!Arrays.equals(file, other.file)) {
			return false;
		}
		if (filepath == null) {
			if (other.filepath != null) {
				return false;
			}
		} else if (!filepath.equals(other.filepath)) {
			return false;
		}
		if (filetype == null) {
			if (other.filetype != null) {
				return false;
			}
		} else if (!filetype.equals(other.filetype)) {
			return false;
		}
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (progress != other.progress) {
			return false;
		}
		if (reportName == null) {
			if (other.reportName != null) {
				return false;
			}
		} else if (!reportName.equals(other.reportName)) {
			return false;
		}
		if (ruleNo == null) {
			if (other.ruleNo != null) {
				return false;
			}
		} else if (!ruleNo.equals(other.ruleNo)) {
			return false;
		}
		if (ruleTitle == null) {
			if (other.ruleTitle != null) {
				return false;
			}
		} else if (!ruleTitle.equals(other.ruleTitle)) {
			return false;
		}
		if (tariff == null) {
			if (other.tariff != null) {
				return false;
			}
		} else if (!tariff.equals(other.tariff)) {
			return false;
		}
		return true;
	}
}
