package net.shopxx.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import net.shopxx.BigDecimalNumericFieldBridge;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.Nullable;

@Entity
@Table(name = "xx_estate_owner_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_owner_info_sequence")
public class OwnerInfo extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6055175699919978345L;

	/**编号*/
	private String no;
	
	/**性别*/
	private String sex;
	
	/**姓名*/
	private String name;

	/**业主照片**/
	private String photo;
	
	/**身份证号*/
	private String identity;
	
	/**学历*/
	private String education;

	/**手机号码**/
	private String phone;
	
	/**水费**/
	private BigDecimal  water_rate;
	
	/**电费**/
	private BigDecimal  power_rate;
	
	/**煤气费**/
	private BigDecimal  gas_rate;	
	
	/**其他维护费**/
	private BigDecimal  other_rate;		
	
	/**总额**/
	private BigDecimal  total_rate;	
	
	/**认证状态**/
	private Long authenticating_state;
	
	
	/** 业主缴费日志 */
	private Set<OwnerPaymentLog> paymentLogs = new HashSet<OwnerPaymentLog>();
	
	/** 小区信息 */
	private Community community;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@NumericField
	@FieldBridge(impl = BigDecimalNumericFieldBridge.class)
	@Min(0)
	@Nullable
	@Digits(integer = 12, fraction = 3)
	@Column(precision = 21, scale = 6)
	public BigDecimal getWater_rate() {
		return water_rate;
	}

	public void setWater_rate(BigDecimal water_rate) {
		this.water_rate = water_rate;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@NumericField
	@FieldBridge(impl = BigDecimalNumericFieldBridge.class)
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(precision = 21, scale = 6)
	public BigDecimal getPower_rate() {
		return power_rate;
	}

	public void setPower_rate(BigDecimal power_rate) {
		this.power_rate = power_rate;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@NumericField
	@FieldBridge(impl = BigDecimalNumericFieldBridge.class)
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(precision = 21, scale = 6)
	public BigDecimal getGas_rate() {
		return gas_rate;
	}

	public void setGas_rate(BigDecimal gas_rate) {
		this.gas_rate = gas_rate;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@NumericField
	@FieldBridge(impl = BigDecimalNumericFieldBridge.class)
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(precision = 21, scale = 6)
	public BigDecimal getOther_rate() {
		return other_rate;
	}

	public void setOther_rate(BigDecimal other_rate) {
		this.other_rate = other_rate;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.UN_TOKENIZED)
	@NumericField
	@FieldBridge(impl = BigDecimalNumericFieldBridge.class)
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(precision = 21, scale = 6)
	public BigDecimal getTotal_rate() {
		return total_rate;
	}

	public void setTotal_rate(BigDecimal total_rate) {
		this.total_rate = total_rate;
	}

	public Long getAuthenticating_state() {
		return authenticating_state;
	}

	public void setAuthenticating_state(Long authenticating_state) {
		this.authenticating_state = authenticating_state;
	}

	@OneToMany(mappedBy = "ownerInfo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("createDate asc")
	public Set<OwnerPaymentLog> getPaymentLogs() {
		return paymentLogs;
	}

	public void setPaymentLogs(Set<OwnerPaymentLog> paymentLogs) {
		this.paymentLogs = paymentLogs;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "communityId", nullable = false)
	public Community getCommunity() {
		return community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}
	
}
