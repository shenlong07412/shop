package net.shopxx.entity.biz;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import net.shopxx.entity.BaseEntity;

/**
 * 银行帐号信息entity
 * 
 * @author lianjsh
 */
@Entity
@Table(name = "xx_biz_bank")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_biz_bank_account_sequence")
public class BankAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Merchant          merchant;             // 商户ID
    private String            bankAccount;          // 公司对公账号
    private String            bankAccountName;      // 开户名
    private String            bankName;             // 银行名
    private String            bankBranchName;       // 银行支行名
    private String            bankAddress;          // 开户银行的地址

    /**
     * @return the merchant
     */
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "merchart_id",insertable=true,updatable=true)
    public Merchant getMerchant() {
        return merchant;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankBranchName() {
        return bankBranchName;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBankBranchName(String bankBranchName) {
        this.bankBranchName = bankBranchName;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

}
