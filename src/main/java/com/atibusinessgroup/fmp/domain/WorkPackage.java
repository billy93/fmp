package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.enumeration.PackageType;
import com.atibusinessgroup.fmp.domain.enumeration.Status;

/**
 * A WorkPackage.
 */
@Document(collection = "work_package")
public class WorkPackage extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private int exportIndex;
    private int importIndex;
    
    @Field("discount")
    private boolean discount;
    
    @Field("addon")
    private boolean addon;

    //MARKET
    @Field("market_fares")
    private boolean marketFares;
    
    @Field("market_rules")
    private boolean marketRules;
    //END MARKET
    
    //WAIVER
    @Field("waiver_fares")
    private boolean waiverFares;

    @Field("waiver_rules")
    private boolean waiverRules;
    //END WAIVER
    
    @Field("filing_instruction")
    private boolean filingInstruction;
    
    @Field("filing_details")
    private boolean filingDetails;
    
    @Field("attachment")
    private boolean attachment;
    
    @Field("specified_fares")
    private boolean specifiedFares;
    
    @Field("locked")
    private boolean locked;
    
    //HEADER
    @Field("exp_pax")
    private String expPax;
    
    @Field("exp_rev")
    private String expRev;
    
    @Field("review_level")
    private String reviewLevel;
    
    @Field("sideway_review_level")
    private String sidewayReviewLevel;
    
    @Field("distribution_review_level")
    private String distributionReviewLevel;
    
    @Field("batch_string")
    private String batchString;
    
    @Field("comment")
    private List<Comment> comment;
    
    @Field("interoffice_comment")
    private List<Comment> interofficeComment;
    
    @Field("ratesheet_comment")
    private String ratesheetComment;
    
    @Field("status")
    private Status status;

    @Field("wpid")
    private String wpid;

    @Field("name")
    private String name;

    @Field("business_area")
    private String businessArea;
    
    @Field("priority")
    private String priority;

    @Field("filing_date")
    private ZonedDateTime filingDate;

    @Field("sale_date")
    private ZonedDateTime saleDate;

    @Field("target_distribution")
    private String targetDistribution;
    
    @Field("distribution_date")
    private ZonedDateTime distributionDate;

    @Field("filling_status")
    private Status fillingStatus;

    @Field("filling_error")
    private String fillingError;

    @Field("locked_by")
    private String lockedBy;

    @Field("locked_since")
    private ZonedDateTime lockedSince;

    @Field("type")
    private PackageType type;

    @Field("contract_fmp")
    private String contractFMP;

    @Field("change_type")
    private String changeType;

    @Field("approval_reference")
    private String approvalReference;

    @Field("fare_sheet")
    private List<WorkPackageFareSheet> fareSheet;
    
    @Field("discount_fare_sheet")
    private List<WorkPackageFareSheet> discountFareSheet;
    
    @Field("addon_fare_sheet")
    private List<WorkPackageFareSheet> addonFareSheet;
    
    @Field("market_fare_sheet")
    private List<WorkPackageFareSheet> marketFareSheet;
    
    @Field("waiver_fare_sheet")
    private List<WorkPackageFareSheet> waiverFareSheet;
    
    @Field("reuse_from")
    private String reuseFrom;
    
    @Field("replace_from")
    private String replaceFrom;
    
    private ApproveConfig approveConfig;
    private ReuseReplaceConfig reuseReplaceConfig;
    
    private Validation validation;
    private boolean validate;
    
    
    public int getImportIndex() {
		return importIndex;
	}

	public void setImportIndex(int importIndex) {
		this.importIndex = importIndex;
	}

	public int getExportIndex() {
		return exportIndex;
	}

	public void setExportIndex(int exportIndex) {
		this.exportIndex = exportIndex;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

	public static class Validation{
    	private List<Tab> tab;
    	private int errorsCount;
    	private int warningsCount;
    	
    	
    	public int getErrorsCount() {
			return errorsCount;
		}

		public void setErrorsCount(int errorsCount) {
			this.errorsCount = errorsCount;
		}

		public int getWarningsCount() {
			return warningsCount;
		}

		public void setWarningsCount(int warningsCount) {
			this.warningsCount = warningsCount;
		}

		public List<Tab> getTab() {
			return tab;
		}

		public void setTab(List<Tab> tab) {
			this.tab = tab;
		}

		public static class Tab{
    		private String name;
    		private List<Error> warning;
    		private List<Error> error;
    		
    		public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}

			public List<Error> getWarning() {
				return warning;
			}

			public void setWarning(List<Error> warning) {
				this.warning = warning;
			}

			public List<Error> getError() {
				return error;
			}

			public void setError(List<Error> error) {
				this.error = error;
			}

			public static class Error{
    			private String message;

				public String getMessage() {
					return message;
				}

				public void setMessage(String message) {
					this.message = message;
				}

				
    			
    		}
    	}
    }
    
    
    public Validation getValidation() {
		return validation;
	}

	public void setValidation(Validation validation) {
		this.validation = validation;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
		if(!this.locked) {
			this.lockedBy = null;
			this.lockedSince = null;
		}
	}

	public ReuseReplaceConfig getReuseReplaceConfig() {
		return reuseReplaceConfig;
	}

	public void setReuseReplaceConfig(ReuseReplaceConfig reuseReplaceConfig) {
		this.reuseReplaceConfig = reuseReplaceConfig;
	}

	public static class ReuseReplaceConfig{
    	public boolean attachment;

		public boolean isAttachment() {
			return attachment;
		}

		public void setAttachment(boolean attachment) {
			this.attachment = attachment;
		}
    }
    
    public static class ApproveConfig{
    	public List<String> email;
    	public List<String> ccEmail;
    	public boolean attachment;
    	
		public List<String> getEmail() {
			return email;
		}

		public void setEmail(List<String> email) {
			this.email = email;
		}

		public boolean isAttachment() {
			return attachment;
		}

		public void setAttachment(boolean attachment) {
			this.attachment = attachment;
		}

		public List<String> getCcEmail() {
			return ccEmail;
		}

		public void setCcEmail(List<String> ccEmail) {
			this.ccEmail = ccEmail;
		}

    }
    
    
	public ApproveConfig getApproveConfig() {
		return approveConfig;
	}

	public void setApproveConfig(ApproveConfig approveConfig) {
		this.approveConfig = approveConfig;
	}

	public String getReuseFrom() {
		return reuseFrom;
	}

	public void setReuseFrom(String reuseFrom) {
		this.reuseFrom = reuseFrom;
	}

	public String getReplaceFrom() {
		return replaceFrom;
	}
	

	public void setReplaceFrom(String replaceFrom) {
		this.replaceFrom = replaceFrom;
	}

	public boolean isWaiverFares() {
		return waiverFares;
	}

	public void setWaiverFares(boolean waiverFares) {
		this.waiverFares = waiverFares;
	}

	public boolean isWaiverRules() {
		return waiverRules;
	}

	public void setWaiverRules(boolean waiverRules) {
		this.waiverRules = waiverRules;
	}

	public boolean isFilingDetails() {
		return filingDetails;
	}

	public void setFilingDetails(boolean filingDetails) {
		this.filingDetails = filingDetails;
	}

	public static class WorkPackageFareSheet{
		

    	@Field("sheet_number")
    	private int sheetNumber;
    	
    	@Field("fares")
    	private List<WorkPackageFare> fares = new ArrayList<>();
    	
    	//Regular ATPCO
        @Field("specified_fares_name")
        private String specifiedFaresName;        

        //Addon Fares
        @Field("addon_fares_name")
        private String addonFaresName;
        
        //Discount Fares
        @Field("discount_fares_name")
        private String discountFaresName;
        
        @Field("discount_fare_type")
        private String discountFareType;
        
        @Field("discount_approval_reference")
        private String discountApprovalReference;
        
        @Field("account_code")
        private String accountCode;
        //End Discount Fares
        
        //Waiver Fares
        @Field("waiver_fares_name")
        private String waiverFaresName;
        
        @Field("waiver_fare_type")
        private String waiverFareType;     
        
        @Field("waiver_approval_reference")
        private String waiverApprovalReference;
        
        @Field("waiver_agent_name")
        private String waiverAgentName;    
        
        @Field("waiver_iata_no")
        private String waiverIataNo;
        
        @Field("waiver_ioc_number")
        private String waiverIocNumber;
        
        @Field("waiver_approval_date")
        private String waiverApprovalDate;
        //End Waiver Fares
        
        //Market Fares
        @Field("market_fares_name")
        private String marketFaresName;
        
        @Field("group_fares")
        private boolean groupFares;
        //End Market Fares
        
    	@Field("fare_carrier")
        private String fareCarrier;
    	
        @Field("approval_reference")
        private String approvalReference;

        @Field("fare_type")
        private String fareType;

        @Field("effective_date")
        private String effectiveDate;

        @Field("discontinue_date")
        private String discontinueDate;

		public String getDiscountApprovalReference() {
			return discountApprovalReference;
		}

		public void setDiscountApprovalReference(String discountApprovalReference) {
			this.discountApprovalReference = discountApprovalReference;
		}

		public String getWaiverIocNumber() {
			return waiverIocNumber;
		}

		public void setWaiverIocNumber(String waiverIocNumber) {
			this.waiverIocNumber = waiverIocNumber;
		}

		public String getWaiverApprovalReference() {
			return waiverApprovalReference;
		}

		public void setWaiverApprovalReference(String waiverApprovalReference) {
			this.waiverApprovalReference = waiverApprovalReference;
		}

		public String getWaiverAgentName() {
			return waiverAgentName;
		}

		public void setWaiverAgentName(String waiverAgentName) {
			this.waiverAgentName = waiverAgentName;
		}

		public String getWaiverIataNo() {
			return waiverIataNo;
		}

		public void setWaiverIataNo(String waiverIataNo) {
			this.waiverIataNo = waiverIataNo;
		}

		public String getWaiverApprovalDate() {
			return waiverApprovalDate;
		}

		public void setWaiverApprovalDate(String waiverApprovalDate) {
			this.waiverApprovalDate = waiverApprovalDate;
		}

		public boolean isGroupFares() {
			return groupFares;
		}

		public void setGroupFares(boolean groupFares) {
			this.groupFares = groupFares;
		}

		public String getWaiverFaresName() {
			return waiverFaresName;
		}

		public void setWaiverFaresName(String waiverFaresName) {
			this.waiverFaresName = waiverFaresName;
		}

		public String getWaiverFareType() {
			return waiverFareType;
		}

		public void setWaiverFareType(String waiverFareType) {
			this.waiverFareType = waiverFareType;
		}

		public String getDiscountFareType() {
			return discountFareType;
		}

		public void setDiscountFareType(String discountFareType) {
			this.discountFareType = discountFareType;
		}
		
        public String getMarketFaresName() {
			return marketFaresName;
		}

		public void setMarketFaresName(String marketFaresName) {
			this.marketFaresName = marketFaresName;
		}

		public int getSheetNumber() {
			return sheetNumber;
		}
        
        public void setSheetNumber(int sheetNumber) {
			this.sheetNumber = sheetNumber;
		}
        
		public List<WorkPackageFare> getFares() {
			return fares;
		}

		public void setFares(List<WorkPackageFare> fares) {
			this.fares = fares;
		}

		public String getSpecifiedFaresName() {
			return specifiedFaresName;
		}

		public void setSpecifiedFaresName(String specifiedFaresName) {
			this.specifiedFaresName = specifiedFaresName;
		}

		public String getFareCarrier() {
			return fareCarrier;
		}

		public void setFareCarrier(String fareCarrier) {
			this.fareCarrier = fareCarrier;
		}

		public String getApprovalReference() {
			return approvalReference;
		}

		public void setApprovalReference(String approvalReference) {
			this.approvalReference = approvalReference;
		}

		public String getFareType() {
			return fareType;
		}

		public void setFareType(String fareType) {
			this.fareType = fareType;
		}

		public String getEffectiveDate() {
			return effectiveDate;
		}

		public void setEffectiveDate(String effectiveDate) {
			this.effectiveDate = effectiveDate;
		}

		public String getDiscontinueDate() {
			return discontinueDate;
		}

		public void setDiscontinueDate(String discontinueDate) {
			this.discontinueDate = discontinueDate;
		}

		public String getAddonFaresName() {
			return addonFaresName;
		}

		public void setAddonFaresName(String addonFaresName) {
			this.addonFaresName = addonFaresName;
		}

		public String getDiscountFaresName() {
			return discountFaresName;
		}

		public void setDiscountFaresName(String discountFaresName) {
			this.discountFaresName = discountFaresName;
		}

		public String getAccountCode() {
			return accountCode;
		}

		public void setAccountCode(String accountCode) {
			this.accountCode = accountCode;
		}
		
		@Field(value="fare_version")
		public List<FareVersion> fareVersion = new ArrayList<>();
		
		public static class FareVersion{
			public List<WorkPackageFare> fares = new ArrayList<>();
			
			public int version;
			public String action;
			public String username;
			public List<WorkPackageFare> getFares() {
				return fares;
			}
			public void setFares(List<WorkPackageFare> fares) {
				this.fares = fares;
			}
			public int getVersion() {
				return version;
			}
			public void setVersion(int version) {
				this.version = version;
			}
			public String getAction() {
				return action;
			}
			public void setAction(String action) {
				this.action = action;
			}
			public String getUsername() {
				return username;
			}
			public void setUsername(String username) {
				this.username = username;
			}
		}
		
		public List<FareVersion> getFareVersion() {
			return fareVersion;
		}

		public void setFareVersion(List<FareVersion> fareVersion) {
			this.fareVersion = fareVersion;
		}		
    }
    
    private List<FilingInstruction> filingInstructionData = new ArrayList<>();
    
    private List<MarketRules> marketRulesData = new ArrayList<>();
    
    private List<Attachment> attachmentData = new ArrayList<>();
    
    private List<String> agent = new ArrayList<>();
    
    private ImportFares importFares;
    
    
    public boolean isMarketFares() {
		return marketFares;
	}

	public void setMarketFares(boolean marketFares) {
		this.marketFares = marketFares;
	}

	public List<WorkPackageFareSheet> getMarketFareSheet() {
		return marketFareSheet;
	}

	public void setMarketFareSheet(List<WorkPackageFareSheet> marketFareSheet) {
		this.marketFareSheet = marketFareSheet;
	}

	public List<WorkPackageFareSheet> getWaiverFareSheet() {
		return waiverFareSheet;
	}

	public void setWaiverFareSheet(List<WorkPackageFareSheet> waiverFareSheet) {
		this.waiverFareSheet = waiverFareSheet;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	public boolean isSpecifiedFares() {
		return specifiedFares;
	}

	public void setSpecifiedFares(boolean specifiedFares) {
		this.specifiedFares = specifiedFares;
	}

	public List<WorkPackageFareSheet> getAddonFareSheet() {
		return addonFareSheet;
	}

	public void setAddonFareSheet(List<WorkPackageFareSheet> addonFareSheet) {
		this.addonFareSheet = addonFareSheet;
	}

	@Field("filing_detail")
    private FilingDetail filingDetail;
    
    public static class ImportFares {
    	@Field("file")
	    private byte[] file;

	    @Field("file_content_type")
	    private String fileContentType;

		public byte[] getFile() {
			return file;
		}

		public void setFile(byte[] file) {
			this.file = file;
		}

		public String getFileContentType() {
			return fileContentType;
		}

		public void setFileContentType(String fileContentType) {
			this.fileContentType = fileContentType;
		}
    }
    
    public static class Comment{
    		public String comment;
    		public String username;
    		public ZonedDateTime createdTime;
			public String getComment() {
				return comment;
			}
			public void setComment(String comment) {
				this.comment = comment;
			}
			public String getUsername() {
				return username;
			}
			public void setUsername(String username) {
				this.username = username;
			}
			public ZonedDateTime getCreatedTime() {
				return createdTime;
			}
			public void setCreatedTime(ZonedDateTime createdTime) {
				this.createdTime = createdTime;
			}
    }
    
    
    public List<Comment> getInterofficeComment() {
		return interofficeComment;
	}

	public void setInterofficeComment(List<Comment> interofficeComment) {
		this.interofficeComment = interofficeComment;
	}

	public String getExpPax() {
		return expPax;
	}

	public void setExpPax(String expPax) {
		this.expPax = expPax;
	}

	public String getExpRev() {
		return expRev;
	}

	public void setExpRev(String expRev) {
		this.expRev = expRev;
	}

	public ZonedDateTime getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(ZonedDateTime saleDate) {
		this.saleDate = saleDate;
	}

	public List<WorkPackageFareSheet> getDiscountFareSheet() {
		return discountFareSheet;
	}

	public void setDiscountFareSheet(List<WorkPackageFareSheet> discountFareSheet) {
		this.discountFareSheet = discountFareSheet;
	}

	public List<WorkPackageFareSheet> getFareSheet() {
		return fareSheet;
	}

	public void setFareSheet(List<WorkPackageFareSheet> fareSheet) {
		this.fareSheet = fareSheet;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

    public List<String> getAgent() {
		return agent;
	}

	public void setAgent(List<String> agent) {
		this.agent = agent;
	}

	public ImportFares getImportFares() {
		return importFares;
	}

	public void setImportFares(ImportFares importFares) {
		this.importFares = importFares;
	}

	public List<Attachment> getAttachmentData() {
		return attachmentData;
	}

	public void setAttachmentData(List<Attachment> attachmentData) {
		this.attachmentData = attachmentData;
	}

	public List<FilingInstruction> getFilingInstructionData() {
		return filingInstructionData;
	}

	public void setFilingInstructionData(List<FilingInstruction> filingInstructionData) {
		this.filingInstructionData = filingInstructionData;
	}
	
	
	
	public List<MarketRules> getMarketRulesData() {
		return marketRulesData;
	}

	public void setMarketRulesData(List<MarketRules> marketRulesData) {
		this.marketRulesData = marketRulesData;
	}

	public String getContractFMP() {
		return contractFMP;
	}

	public void setContractFMP(String contractFMP) {
		this.contractFMP = contractFMP;
	}

	public static class Attachment{
		private String comment;
		
	    @Field("file")
	    private byte[] file;

	    @Field("file_content_type")
	    private String fileContentType;

	    @Field("username")	    
	    private String username;
	    
	    @Field("createdTime")	    
		private ZonedDateTime createdTime;
	    
	    @Field("inOnly")
	    private Boolean inOnly;
		
		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public byte[] getFile() {
			return file;
		}

		public void setFile(byte[] file) {
			this.file = file;
		}

		public String getFileContentType() {
			return fileContentType;
		}

		public void setFileContentType(String fileContentType) {
			this.fileContentType = fileContentType;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public ZonedDateTime getCreatedTime() {
			return createdTime;
		}

		public void setCreatedTime(ZonedDateTime createdTime) {
			this.createdTime = createdTime;
		}

		public Boolean getInOnly() {
			return inOnly;
		}

		public void setInOnly(Boolean inOnly) {
			this.inOnly = inOnly;
		}	
		
	}


	public static class FilingInstruction{
    		private String status;
    		private String tarno;
    		private String tarcd;
    		private String cxr;
    		private String comment;
    		private boolean isDeleted;
    		
    	    @Field("file")
    	    private byte[] file;

    	    @Field("file_content_type")
    	    private String fileContentType;


    	    @Field("username")	    
    	    private String username;
    	    
    	    @Field("createdTime")	    
    		private ZonedDateTime createdTime;
    	    
    	    
			public boolean getIsDeleted() {
				return isDeleted;
			}

			public void setIsDeleted(boolean isDeleted) {
				this.isDeleted = isDeleted;
			}

			public String getStatus() {
				return status;
			}

			public void setStatus(String status) {
				this.status = status;
			}

			public String getTarno() {
				return tarno;
			}

			public void setTarno(String tarno) {
				this.tarno = tarno;
			}

			public String getTarcd() {
				return tarcd;
			}

			public void setTarcd(String tarcd) {
				this.tarcd = tarcd;
			}

			public String getCxr() {
				return cxr;
			}

			public void setCxr(String cxr) {
				this.cxr = cxr;
			}

			public String getComment() {
				return comment;
			}

			public void setComment(String comment) {
				this.comment = comment;
			}

			public byte[] getFile() {
				return file;
			}

			public void setFile(byte[] file) {
				this.file = file;
			}

			public String getFileContentType() {
				return fileContentType;
			}

			public void setFileContentType(String fileContentType) {
				this.fileContentType = fileContentType;
			}

			public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}

			public ZonedDateTime getCreatedTime() {
				return createdTime;
			}

			public void setCreatedTime(ZonedDateTime createdTime) {
				this.createdTime = createdTime;
			}
    }
	
	public static class MarketRules{
    		private String status;
    		private String username;
    		private String ruleid;
    		private String cxr;
    		private String comment;
    		
    	    @Field("file")
    	    private byte[] file;

    	    @Field("file_content_type")
    	    private String fileContentType;

    	    @Field("createdTime")	    
    		private ZonedDateTime createdTime;
    	    
    	   
		public String getUsername() {
				return username;
			}

			public void setUsername(String username) {
				this.username = username;
			}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getRuleid() {
			return ruleid;
		}

		public void setRuleid(String ruleid) {
			this.ruleid = ruleid;
		}
		
		public String getCxr() {
			return cxr;
		}

		public void setCxr(String cxr) {
			this.cxr = cxr;
		}

		public String getComment() {
			return comment;
		}

		public void setComment(String comment) {
			this.comment = comment;
		}

		public byte[] getFile() {
			return file;
		}

		public void setFile(byte[] file) {
			this.file = file;
		}

		public String getFileContentType() {
			return fileContentType;
		}

		public void setFileContentType(String fileContentType) {
				this.fileContentType = fileContentType;
			}

		public ZonedDateTime getCreatedTime() {
			return createdTime;
		}

		public void setCreatedTime(ZonedDateTime createdTime) {
			this.createdTime = createdTime;
		}
   
		
	}
	
    public static class FilingDetail{
    		private String email;

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
    }
    
    public FilingDetail getFilingDetail() {
		return filingDetail;
	}
    
    public void setFilingDetail(FilingDetail filingDetail) {
		this.filingDetail = filingDetail;
	}
    
	public String getBatchString() {
		return batchString;
	}

	public void setBatchString(String batchString) {
		this.batchString = batchString;
	}

	public ZonedDateTime getFilingDate() {
		return filingDate;
	}

	public void setFilingDate(ZonedDateTime filingDate) {
		this.filingDate = filingDate;
	}

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    
    public String getApprovalReference() {
		return approvalReference;
	}

	public void setApprovalReference(String approvalReference) {
		this.approvalReference = approvalReference;
	}

	public Status getStatus() {
        return status;
    }

    public WorkPackage status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getWpid() {
        return wpid;
    }

    public WorkPackage wpid(String wpid) {
        this.wpid = wpid;
        return this;
    }

    public void setWpid(String wpid) {
        this.wpid = wpid;
    }

    public String getName() {
        return name;
    }

    public WorkPackage name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public ZonedDateTime getDistributionDate() {
        return distributionDate;
    }

    public WorkPackage distributionDate(ZonedDateTime distributionDate) {
        this.distributionDate = distributionDate;
        return this;
    }

    public void setDistributionDate(ZonedDateTime distributionDate) {
        this.distributionDate = distributionDate;
    }

    public Status getFillingStatus() {
        return fillingStatus;
    }

    public WorkPackage fillingStatus(Status fillingStatus) {
        this.fillingStatus = fillingStatus;
        return this;
    }

    public void setFillingStatus(Status fillingStatus) {
        this.fillingStatus = fillingStatus;
    }

    public String getFillingError() {
        return fillingError;
    }

    public WorkPackage fillingError(String fillingError) {
        this.fillingError = fillingError;
        return this;
    }

    public void setFillingError(String fillingError) {
        this.fillingError = fillingError;
    }

    public boolean isAddon() {
		return addon;
	}

	public void setAddon(boolean addon) {
		this.addon = addon;
	}

	public boolean isMarketRules() {
		return marketRules;
	}

	public void setMarketRules(boolean marketRules) {
		this.marketRules = marketRules;
	}

	public boolean isFilingInstruction() {
		return filingInstruction;
	}

	public void setFilingInstruction(boolean filingInstruction) {
		this.filingInstruction = filingInstruction;
	}

	public boolean isAttachment() {
		return attachment;
	}

	public void setAttachment(boolean attachment) {
		this.attachment = attachment;
	}

	public String getLockedBy() {
        return lockedBy;
    }

    public WorkPackage lockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
        return this;
    }

    public void setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
    }

    public ZonedDateTime getLockedSince() {
        return lockedSince;
    }

    public WorkPackage lockedSince(ZonedDateTime lockedSince) {
        this.lockedSince = lockedSince;
        return this;
    }

    public void setLockedSince(ZonedDateTime lockedSince) {
        this.lockedSince = lockedSince;
    }

    public PackageType getType() {
        return type;
    }

    public WorkPackage type(PackageType type) {
        this.type = type;
        return this;
    }

    public void setType(PackageType type) {
        this.type = type;
    }
    
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    

	public String getRatesheetComment() {
		return ratesheetComment;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public void setRatesheetComment(String ratesheetComment) {
		this.ratesheetComment = ratesheetComment;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkPackage workPackage = (WorkPackage) o;
        if (workPackage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workPackage.getId());
    }

//    public ProductType getProductType() {
//		return productType;
//	}
//
//	public void setProductType(ProductType productType) {
//		this.productType = productType;
//	}

//	public BusinessArea getBusinessArea() {
//		return businessArea;
//	}
//
//	public void setBusinessArea(BusinessArea businessArea) {
//		this.businessArea = businessArea;
//	}

	public String getReviewLevel() {
		return reviewLevel;
	}

	public void setReviewLevel(String reviewLevel) {
		this.reviewLevel = reviewLevel;
	}

//	public ProductSubtype getProductSubtype() {
//		return productSubtype;
//	}
//
//	public void setProductSubtype(ProductSubtype productSubtype) {
//		this.productSubtype = productSubtype;
//	}

//	public TargetDistribution getTargetDistribution() {
//		return targetDistribution;
//	}
//
//	public void setTargetDistribution(TargetDistribution targetDistribution) {
//		this.targetDistribution = targetDistribution;
//	}

	@Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	public String getBusinessArea() {
		return businessArea;
	}

	public void setBusinessArea(String businessArea) {
		this.businessArea = businessArea;
	}

	public String getTargetDistribution() {
		return targetDistribution;
	}

	public void setTargetDistribution(String targetDistribution) {
		this.targetDistribution = targetDistribution;
	}

	
	public String getSidewayReviewLevel() {
		return sidewayReviewLevel;
	}

	public void setSidewayReviewLevel(String sidewayReviewLevel) {
		this.sidewayReviewLevel = sidewayReviewLevel;
	}

	public String getDistributionReviewLevel() {
		return distributionReviewLevel;
	}

	public void setDistributionReviewLevel(String distributionReviewLevel) {
		this.distributionReviewLevel = distributionReviewLevel;
	}
	
}
