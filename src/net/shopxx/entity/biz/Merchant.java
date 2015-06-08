package net.shopxx.entity.biz;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.shopxx.entity.BaseEntity;
import net.shopxx.entity.Community;
import net.shopxx.entity.Order;
import net.shopxx.entity.Product;
import net.shopxx.entity.ProductMerchantCategory;
import net.shopxx.entity.virtual.VirAccount;

/**
 * 商户entitity
 * 
 * @author lianjsh
 */
@Entity
@Table(name = "xx_biz_merchant")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_biz_merchant_sequence")
public class Merchant extends BaseEntity {

    /**
	 * 
	 */
    private static final long serialVersionUID = -2854347135736181171L;

    private String            bestpayAccount;                          // 绑定的翼支付帐号
    private String            ivrAccount;                              // ivr的主叫号码
    private String            qrcode;                                  // 二维码本身包含的信息
    private String            homePage;                                // 微官网的首页
    private String            introduction;                            // 微官网的简介
    private int               profit;                                  // 是否盈利：0）不盈利；1）盈利
    private Long              rank;                                    // 商户等级，默认为1级
    private Long              auditSwitch;                             // 审核开关：0）关，不需要审核；1）开，需要审核
    private String            pushFrequency;                           // 为空时，不限制；每M天推送N次，表示为M,N（逗号分隔）
    private Long              channelId;                               // 栏目ID
    private Long              billingType;                             // 结算类型:1:一级结算 2：二级结算
    private Long              billingCycle;                            // 结算周期，单位：天
    private Double            royaltyRate;                             // 提成比率，单位：%
    private Long              supportedRebate;                         // 是否支持返利：0不支持；1支持， 默认不支持
    private String            bestpayId;                               // 翼支付商户编号
    private String            besttoneId;                              // 号百id
    private double            longitude;                               // 经度
    private double            latitude;                                // 纬度
    private String            tag;                                     // 商户标签
    private String            address;                                 // 商户的地址
    private String            merchantNo;                              // 社区O2O管理平台商户编号
    private String            phone;                                   // 电话
    private String            name;                                    // 商户名称
    private String            email;                                   // 邮箱
    private String            logo;                                    // 图标
    private String            fax;                                     // 传真
    private double            discount;                                // 折扣率
    private int               recommend;                               // 是否推荐：0 非推荐商户，即普通商户 ，1推荐商户
    private int               operator;                                // 是否是运营商运营：0 非运营，即商家， 1运营商
    private int               operated;                                // 是否运营： 0 不运营的，即微名片， 1 运营的
    /*
     * private int communityId; // 是否是社区商户：0非社区商户， 非0 社区商户, 关联的社区ID
     */private Long           templateId;                              // 是否使用通用模板： 0 定制类，非0 通用类，关联的模板ID

    private String            busiBtime;                               // 每天营业开始时间
    private String            busiEtime;                               // 每天营业结束时间
    private Long              merchantGradeId;                         // 商户级别ID
    private String            details;                                 // 商户详情
    private int               expressPrice;                            // 配送费用
    private int               deliverLowestPrice;                      // 起送金额
    private String            favorCount;                              // 用户点赞次数

    private Account           account;                                 // 帐户信息
    private Enterprise        enterprise;                              // 企业信息
    private LegalPerson       legalPerson;                             // 法人信息
    private BankAccount       bankAccount;                             // 银行信息
    
    private Set<VirAccount>   virAccounts   = new HashSet<VirAccount>();// 虚拟账号

    /** 小区信息 */
    private Community         community;

    /** 订单 */
    private Set<Order>        orders           = new HashSet<Order>();

    public Double getRoyaltyRate() {
        return royaltyRate;
    }
    
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<VirAccount> getVirAccounts() {
		return virAccounts;
	}

	public void setVirAccounts(Set<VirAccount> virAccounts) {
		this.virAccounts = virAccounts;
	}

	public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getBillingType() {
        return billingType;
    }

    public void setBillingType(Long billingType) {
        this.billingType = billingType;
    }

    public Long getBillingCycle() {
        return billingCycle;
    }

    public void setBillingCycle(Long billingCycle) {
        this.billingCycle = billingCycle;
    }

    public Long getSupportedRebate() {
        return supportedRebate;
    }

    public void setSupportedRebate(Long supportedRebate) {
        this.supportedRebate = supportedRebate;
    }

    public String getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(String favorCount) {
        this.favorCount = favorCount;
    }

    public String getBusiBtime() {
        return busiBtime;
    }

    public void setBusiBtime(String busiBtime) {
        this.busiBtime = busiBtime;
    }

    public String getBusiEtime() {
        return busiEtime;
    }

    public void setBusiEtime(String busiEtime) {
        this.busiEtime = busiEtime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(int expressPrice) {
        this.expressPrice = expressPrice;
    }

    public int getDeliverLowestPrice() {
        return deliverLowestPrice;
    }

    public void setDeliverLowestPrice(int deliverLowestPrice) {
        this.deliverLowestPrice = deliverLowestPrice;
    }

    public String getBestpayAccount() {
        return bestpayAccount;
    }

    public String getIvrAccount() {
        return ivrAccount;
    }

    public String getQrcode() {
        return qrcode;
    }

    public String getHomePage() {
        return homePage;
    }

    public String getIntroduction() {
        return introduction;
    }

    public int getProfit() {
        return profit;
    }

    public Long getAuditSwitch() {
        return auditSwitch;
    }

    public String getPushFrequency() {
        return pushFrequency;
    }

    public void setBestpayAccount(String bestpayAccount) {
        this.bestpayAccount = bestpayAccount;
    }

    public void setIvrAccount(String ivrAccount) {
        this.ivrAccount = ivrAccount;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public void setAuditSwitch(Long auditSwitch) {
        this.auditSwitch = auditSwitch;
    }

    public void setPushFrequency(String pushFrequency) {
        this.pushFrequency = pushFrequency;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public void setRoyaltyRate(Double royaltyRate) {
        this.royaltyRate = royaltyRate;
    }

    public String getBestpayId() {
        return bestpayId;
    }

    public void setBestpayId(String bestpayId) {
        this.bestpayId = bestpayId;
    }

    public String getBesttoneId() {
        return besttoneId;
    }

    public void setBesttoneId(String besttoneId) {
        this.besttoneId = besttoneId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getRecommend() {
        return recommend;
    }

    public int getOperator() {
        return operator;
    }

    public int getOperated() {
        return operated;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public Long getMerchantGradeId() {
        return merchantGradeId;
    }

    public void setMerchantGradeId(Long merchantGradeId) {
        this.merchantGradeId = merchantGradeId;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public void setOperator(int operator) {
        this.operator = operator;
    }

    public void setOperated(int operated) {
        this.operated = operated;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    /**
     * @return the account
     */
    // mappedBy 指明双向关系的维护段 负责外键的更新 起主导作用
    @OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "merchant")
    public Account getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * @return the enterprise
     */
    @OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "merchant")
    public Enterprise getEnterprise() {
        return enterprise;
    }

    /**
     * @param enterprise the enterprise to set
     */
    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    /**
     * @return the legalPerson
     */
    @OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "merchant")
    public LegalPerson getLegalPerson() {
        return legalPerson;
    }

    /**
     * @param legalPerson the legalPerson to set
     */
    public void setLegalPerson(LegalPerson legalPerson) {
        this.legalPerson = legalPerson;
    }

    /**
     * @return the bankAccount
     */
    @OneToOne(cascade = CascadeType.ALL, optional = true, mappedBy = "merchant")
    public BankAccount getBankAccount() {
        return bankAccount;
    }

    /**
     * @param bankAccount the bankAccount to set
     */
    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY)
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    // 商品
    private Set<Product> products = new HashSet<Product>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchant", fetch = FetchType.LAZY)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    // 商品类别授权
    private Set<ProductMerchantCategory> productMerchantCategorys = new HashSet<ProductMerchantCategory>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchant", fetch = FetchType.LAZY)
    public Set<ProductMerchantCategory> getProductMerchantCategorys() {
        return productMerchantCategorys;
    }

    public void setProductMerchantCategorys(Set<ProductMerchantCategory> productMerchantCategorys) {
        this.productMerchantCategorys = productMerchantCategorys;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "community_id", nullable = false, updatable = true)
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

}
