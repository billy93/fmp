package com.atibusinessgroup.fmp.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A RbdColorMapping.
 */
@Document(collection = "rbd_color_mapping")
public class RbdColorMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("rbd")
    private String rbd;

    @Field("color")
    private String color;
    
    @Field("colorVal")
    private String colorVal;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRbd() {
        return rbd;
    }

    public RbdColorMapping rbd(String rbd) {
        this.rbd = rbd;
        return this;
    }

    public void setRbd(String rbd) {
        this.rbd = rbd;
    }

    public String getColor() {
        return color;
    }

    public RbdColorMapping color(String color) {
        this.color = color;
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

	public String getColorVal() {
		return colorVal;
	}

	public void setColorVal(String colorVal) {
		this.colorVal = colorVal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((colorVal == null) ? 0 : colorVal.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((rbd == null) ? 0 : rbd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RbdColorMapping other = (RbdColorMapping) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (colorVal == null) {
			if (other.colorVal != null)
				return false;
		} else if (!colorVal.equals(other.colorVal))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (rbd == null) {
			if (other.rbd != null)
				return false;
		} else if (!rbd.equals(other.rbd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RbdColorMapping [id=" + id + ", rbd=" + rbd + ", color=" + color + ", colorVal=" + colorVal + "]";
	}
    
    
    

	
}
