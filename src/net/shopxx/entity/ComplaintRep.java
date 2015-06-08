package net.shopxx.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity - 投诉保修表
 * 
 * @author
 * @version 1.0
 */
@Entity
@Table(name = "xx_estate_complaint_reporting")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_complaint_reporting_sequence")
public class ComplaintRep extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 45207402095348073L;
	
	/**投诉保修人*/
	private String person;
	
	/**投诉保修信息*/
	private String content;	
	
	/**联系电话*/
	private String phone;
	
	/**是否公开*/
	private Long isPublic;
	
	/**支持人数*/
	private Long supportNumber;
	
	/**处理状态*/
	private Long status;	
	
	/**当前处理人*/
	private String handing;
	
	/**联系地址*/
	private String address;

	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/** 图片信息 */
	private Set<ComplaintRepAnnex> annexs = new HashSet<ComplaintRepAnnex>();
	
	/** 日志信息 */
	private Set<ComplaintRepLog> logs = new HashSet<ComplaintRepLog>();

	
	
	private Community community;
	
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Long isPublic) {
		this.isPublic = isPublic;
	}

	public Long getSupportNumber() {
		return supportNumber;
	}

	public void setSupportNumber(Long supportNumber) {
		this.supportNumber = supportNumber;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getHanding() {
		return handing;
	}

	public void setHanding(String handing) {
		this.handing = handing;
	}

   

	@OneToMany(mappedBy = "coRep", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("createDate asc")
	public Set<ComplaintRepAnnex> getAnnexs() {
		return annexs;
	}
	
	public void setAnnexs(Set<ComplaintRepAnnex> annexs) {
		this.annexs = annexs;
	}

	@OneToMany(mappedBy = "coRep", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("createDate asc")
	public Set<ComplaintRepLog> getLogs() {
		return logs;
	}

	public void setLogs(Set<ComplaintRepLog> logs) {
		this.logs = logs;
	}

}
