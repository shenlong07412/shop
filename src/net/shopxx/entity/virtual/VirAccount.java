package net.shopxx.entity.virtual;

import java.math.BigDecimal;
import java.util.Date;
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

import net.shopxx.entity.BaseEntity;
import net.shopxx.entity.Member;
import net.shopxx.entity.biz.Merchant;

/**
 * 虚拟账号
 * 
 * @author chenshw
 */
@Entity
@Table(name = "xx_virtual_account")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_virtual_account_sequence")
public class VirAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Merchant          merchant;                                 // 商户
    private Member			  member;									// 会员
    private String            nickname;                                 // 昵称
    private String            tradePasswd;                              // 交易密码
    private String			  serialNumber;								// 编号序列号
     
    private Date              lastTradeDate;                            // 上一次支出时间
    private String            lastTradeIp; 								// 上一次支出IP
    
    private Date              lastRechargeDate;                         // 上一次充值时间
    private String            lastRechargeIp;                           // 上一次充值IP
    
    private int               status;                                   // 状态，0：解冻、1：冻结，默认为解冻状态
   
    private BigDecimal		  rebateAmounts;							//商家   回赠  金额 当前总额
    private BigDecimal		  rechargeAmounts;							//预存金额    总额
	
	private Set<VirTradeLog>  virTradeLogs = new HashSet<VirTradeLog>();//交易记录
	
	
	@OneToMany(mappedBy = "virAccount", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("createDate asc")
	public Set<VirTradeLog> getVirTradeLogs() {
		return virTradeLogs;
	}
	public void setVirTradeLogs(Set<VirTradeLog> virTradeLogs) {
		this.virTradeLogs = virTradeLogs;
	}
		
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "merchant_id", nullable = false, updatable = true)
    public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "member_id", nullable = false, updatable = true)
	public Member getMember() {
		return member;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getTradePasswd() {
		return tradePasswd;
	}
	public void setTradePasswd(String tradePasswd) {
		this.tradePasswd = tradePasswd;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Date getLastTradeDate() {
		return lastTradeDate;
	}
	public void setLastTradeDate(Date lastTradeDate) {
		this.lastTradeDate = lastTradeDate;
	}
	public String getLastTradeIp() {
		return lastTradeIp;
	}
	public void setLastTradeIp(String lastTradeIp) {
		this.lastTradeIp = lastTradeIp;
	}
	public Date getLastRechargeDate() {
		return lastRechargeDate;
	}
	public void setLastRechargeDate(Date lastRechargeDate) {
		this.lastRechargeDate = lastRechargeDate;
	}
	public String getLastRechargeIp() {
		return lastRechargeIp;
	}
	public void setLastRechargeIp(String lastRechargeIp) {
		this.lastRechargeIp = lastRechargeIp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public BigDecimal getRebateAmounts() {
		return rebateAmounts;
	}
	public void setRebateAmounts(BigDecimal rebateAmounts) {
		this.rebateAmounts = rebateAmounts;
	}
	public BigDecimal getRechargeAmounts() {
		return rechargeAmounts;
	}
	public void setRechargeAmounts(BigDecimal rechargeAmounts) {
		this.rechargeAmounts = rechargeAmounts;
	}
    
  

   

}
