package net.shopxx.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "xx_estate_owner_payment_log")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_owner_payment_log_sequence")
public class OwnerPaymentLog extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6055175699919978345L;

	/**流水单号*/
	private String batch_no;
	
	/**业主信息*/
	private OwnerInfo ownerInfo;
	
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
	
	@Column(nullable = false,length = 100)
	public String getBatch_no() {
		return batch_no;
	}

	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ownerInfoId", nullable = false, updatable = false)
	public OwnerInfo getOwnerInfo() {
		return ownerInfo;
	}

	public void setOwnerInfo(OwnerInfo ownerInfo) {
		this.ownerInfo = ownerInfo;
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
	
}
