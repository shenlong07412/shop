package net.shopxx.entity.biz;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.shopxx.entity.BaseEntity;

/**
 * 商户的企业基本信息
 * 
 * @author lianjsh
 */
@Entity
@Table(name = "xx_biz_enterprise")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_biz_enterprise_sequence")
public class Enterprise extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Merchant          merchant;              // 商户ID
    private String            enterpriseName;        // 企业名称
    private String            businessLicenseNo;     // 营业执照号码
    private String            businessLicenseAddress; // 营业执照所在地
    private String            businessPeriod;        // 营业期限
    private String            businessRange;         // 营业范围
    private String            fax;                   // 传真
    private String            phone;                 // 电话，多个逗号分隔
    private String            email;
    private String            registeredCapital;     // 注册资金
    private String            organizationCode;      // 组织机构代码
    private String            industry;              // 所属行业名称
    private String            busRoute;              // 乘车路线
    private String            city;

    /**
     * @return the merchant
     */
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "merchart_id", referencedColumnName = "id", unique = true)
    public Merchant getMerchant() {
        return merchant;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public String getBusinessLicenseAddress() {
        return businessLicenseAddress;
    }

    public String getBusinessPeriod() {
        return businessPeriod;
    }

    public String getBusinessRange() {
        return businessRange;
    }

    public String getFax() {
        return fax;
    }

    public String getPhone() {
        return phone;
    }

    public String getRegisteredCapital() {
        return registeredCapital;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public String getIndustry() {
        return industry;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public void setBusinessLicenseAddress(String businessLicenseAddress) {
        this.businessLicenseAddress = businessLicenseAddress;
    }

    public void setBusinessPeriod(String businessPeriod) {
        this.businessPeriod = businessPeriod;
    }

    public void setBusinessRange(String businessRange) {
        this.businessRange = businessRange;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegisteredCapital(String registeredCapital) {
        this.registeredCapital = registeredCapital;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(String busRoute) {
        this.busRoute = busRoute;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
