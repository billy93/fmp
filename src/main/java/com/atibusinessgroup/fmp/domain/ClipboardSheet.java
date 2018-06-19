package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.atibusinessgroup.fmp.domain.WorkPackage.WorkPackageFareSheet;

/**
 * A Clipboard.
 */
@Document(collection = "clipboard_sheet")
public class ClipboardSheet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("username")
    private String username;

    @Field("sheet")
    private WorkPackageFareSheet sheet;
    
    @Field("type")
    private String type;
    
    @Field("copy_date_time")
    private Instant copyDateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public WorkPackageFareSheet getSheet() {
		return sheet;
	}

	public void setSheet(WorkPackageFareSheet sheet) {
		this.sheet = sheet;
	}

	public Instant getCopyDateTime() {
		return copyDateTime;
	}

	public void setCopyDateTime(Instant copyDateTime) {
		this.copyDateTime = copyDateTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    
}
