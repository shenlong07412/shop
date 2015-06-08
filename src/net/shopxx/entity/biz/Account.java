package net.shopxx.entity.biz;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import net.shopxx.entity.BaseEntity;
import net.shopxx.entity.Order;
import net.shopxx.entity.Role;

/**
 * 账号entity
 * 
 * @author lianjsh
 */
@Entity
@Table(name = "xx_biz_account")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_biz_account_sequence")
public class Account extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private Merchant          merchant;                                 // 商户
    private String            username;                                 // 用户名
    private String            password;                                 // 密码
    private String            avatar;                                   // 头像地址
    private String            realname;                                 // 真实姓名
    private String            nickname;                                 // 昵称
    private Date              createdDate;                              // 账号的注册或创建时间
    private Date              lastLoginDate;                            // 上一次登录时间
    private String            lastLoginIp;                              // 上一次登录IP
    private long              loginCount;                               // 登录次数
    private int               status;                                   // 状态，0：解冻、1：冻结，默认为解冻状态
    private Date              updatedDate;                              // 账号信息修改的时间
    private String            idCardNo;                                 // 身份证号码
    private String            phone;                                    // 手机号，唯一性
    private String            email;                                    // 邮箱
    private String            address;                                  // 地址
    private boolean           gender;                                   // 性别: true 男，false 女

    private Account           parent;                                   // 父级帐户
    private Set<Account>      children         = new HashSet<Account>();

    /**
     * 账号类型：1、用户, 2、商户, 3、运营商, 4、系统管理员
     */
    private int               type;

    /** 角色 */
    private Set<Role>         roles            = new HashSet<Role>();

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    /**
     * @return the parent
     */
    @ManyToOne(fetch = FetchType.LAZY)
    public Account getParent() {
        return parent;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getRealname() {
        return realname;
    }

    public String getNickname() {
        return nickname;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public long getLoginCount() {
        return loginCount;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Account parent) {
        this.parent = parent;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public void setLoginCount(long loginCount) {
        this.loginCount = loginCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    /**
     * @return the merchant
     */
    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "merchart_id", referencedColumnName = "id", unique = false, insertable = true, updatable = true)
    public Merchant getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    /**
     * 获取下级地区
     * 
     * @return 下级地区
     */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Account> getChildren() {
        return children;
    }

    /**
     * 设置下级地区
     * 
     * @param children 下级地区
     */
    public void setChildren(Set<Account> children) {
        this.children = children;
    }

    /**
     * 获取角色
     * 
     * @return 角色
     */
    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "xx_biz_role")
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * 设置角色
     * 
     * @param roles 角色
     */
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
