package net.shopxx.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "xx_sensitivity")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_sensitivity_sequence")
public class Sensitivity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6055175699919978345L;

	/**关键字*/
	private String search;
	
	/**替换字符*/
	private String replacement;
	
	/**类型*/
	private Long type;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getReplacement() {
		return replacement;
	}

	public void setReplacement(String replacement) {
		this.replacement = replacement;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	
}
