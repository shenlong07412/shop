package net.shopxx.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "xx_area_community")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_area_community_sequence")
public class AreaForCommunity extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8800651443808664635L;
	private long parentId;
	private String name;
	private String type;

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
