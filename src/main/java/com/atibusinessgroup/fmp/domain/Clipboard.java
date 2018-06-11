package com.atibusinessgroup.fmp.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * A Clipboard.
 */
@Document(collection = "clipboard")
public class Clipboard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("username")
    private String username;

    @Field("page")
    private String page;

    @Field("content")
    private Map<String, List<Object>> content = new HashMap<>();
    
    @Field("copy_date_time")
    private Instant copyDateTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Clipboard username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPage() {
        return page;
    }

    public Clipboard page(String page) {
        this.page = page;
        return this;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Instant getCopyDateTime() {
        return copyDateTime;
    }

    public Clipboard copyDateTime(Instant copyDateTime) {
        this.copyDateTime = copyDateTime;
        return this;
    }

    public void setCopyDateTime(Instant copyDateTime) {
        this.copyDateTime = copyDateTime;
    }
    
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Clipboard clipboard = (Clipboard) o;
        if (clipboard.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), clipboard.getId());
    }

    public Map<String, List<Object>> getContent() {
		return content;
	}

	public void setContent(Map<String, List<Object>> content) {
		this.content = content;
	}

	@Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

	@Override
	public String toString() {
		return "Clipboard [id=" + id + ", username=" + username + ", page=" + page + ", content="
				+ content + ", copyDateTime=" + copyDateTime + "]";
	}
}
