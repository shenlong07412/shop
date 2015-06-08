/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package net.shopxx.entity.virtual;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.shopxx.entity.BaseEntity;

/**
 * Entity - 订单日志
 * 
 * @author SHOP++ Team
 * @version 3.0
 */
@Entity
@Table(name = "xx_virtual_tradeLog")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_virtual_tradeLog_sequence")
public class VirTradeLog extends BaseEntity {

	private static final long serialVersionUID = -2704154761295319939L;

	/**
	 * 类型
	 */
	public enum Type {
		
		/** 充值 */
		recharge,

		/** 消费 */
		consume,
		
		/** 商家回赠 */
		rebate,

		/** 商家退款 */
		refund
	};
	
	/** 类型 */
	private Type type;

	/** 操作员 */
	private String operator;

	/** 内容 */
	private String content;

	/** 虚拟账号*/
	private VirAccount virAccount;
		
	/** 支付手续费 */
	private BigDecimal fee;

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	private BigDecimal		  rebateAmount;								//商家 回赠  金额变化
	private BigDecimal		  rechargeAmount;							//预存金额    变化
	
	public BigDecimal getRebateAmount() {
		return rebateAmount;
	}

	public void setRebateAmount(BigDecimal rebateAmount) {
		this.rebateAmount = rebateAmount;
	}

	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	/**
	 * 获取类型
	 * 
	 * @return 类型
	 */
	@Column(nullable = false, updatable = false)
	public Type getType() {
		return type;
	}

	/**
	 * 设置类型
	 * 
	 * @param type
	 *            类型
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * 获取操作员
	 * 
	 * @return 操作员
	 */
	@Column(updatable = false)
	public String getOperator() {
		return operator;
	}

	/**
	 * 设置操作员
	 * 
	 * @param operator
	 *            操作员
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	@Column(updatable = false)
	public String getContent() {
		return content;
	}

	/**
	 * 设置内容
	 * 
	 * @param content
	 *            内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "virtual_account_id", nullable = false, updatable = false)
	public VirAccount getVirAccount() {
		return virAccount;
	}

	public void setVirAccount(VirAccount virAccount) {
		this.virAccount = virAccount;
	}

	
}