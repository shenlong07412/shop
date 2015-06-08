package net.shopxx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

/**
 * Entity - 投诉保修图片表
 * 
 * @author
 * @version 1.0
 */
@Entity
@Table(name = "xx_estate_complaint_reporting_annex")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_complaint_reporting_annex_sequence")
public class ComplaintRepAnnex extends BaseEntity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3311291564223761239L;

	/**优先级*/
	private Long priority;
	
	/**图片地址*/
	private String imgUrl;
	
	/**投诉维护信息*/
	private ComplaintRep coRep;

	@Min(0)
	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

	@Column(length = 100)
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ComplaintRepId", nullable = false, updatable = false)
	public ComplaintRep getCoRep() {
		return coRep;
	}

	public void setCoRep(ComplaintRep coRep) {
		this.coRep = coRep;
	}
	
	
}
