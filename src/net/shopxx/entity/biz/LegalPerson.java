package net.shopxx.entity.biz;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.shopxx.entity.BaseEntity;

/**
 * 商户的企业法人信息entity
 * 
 * @author lianjsh
 */
@Entity
@Table(name = "xx_biz_leagal_person")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_biz_leagal_person_sequence")
public class LegalPerson extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Merchant          merchant;             // 商户ID
    private String            realname;             // 企业法人的真实姓名
    private String            idCardNo;             // 企业法人的身份证号
    private String            phone;                // 企业法人的电话
    private String            address;              // 企业法人的归属地

    /**
     * @return the merchant
     */
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "merchart_id", referencedColumnName = "id", unique = true)
    public Merchant getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getRealname() {
        return realname;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
