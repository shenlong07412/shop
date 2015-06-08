package net.shopxx.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity - 投诉保修日志表
 * 
 * @author 
 * @version 1.0
 */
@Entity
@Table(name = "xx_estate_complaint_reporting_log")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_complaint_reporting_log_sequence")
public class ComplaintRepLog extends BaseEntity{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 507496253860613813L;
	

	/**处理人*/
	private String person;
	
	/**处理时间*/
	private Date handing_time;
	
	/**处理状态*/
	private Long satus;
	
	/**处理内容*/
	private String content;
	
	/**投诉维护信息*/
	private ComplaintRep coRep;

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public Date getHanding_time() {
		return handing_time;
	}

	public void setHanding_time(Date handing_time) {
		this.handing_time = handing_time;
	}

	public Long getSatus() {
		return satus;
	}

	public void setSatus(Long satus) {
		this.satus = satus;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
