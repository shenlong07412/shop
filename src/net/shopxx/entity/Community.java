package net.shopxx.entity;

import java.util.Date;
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
import javax.validation.constraints.NotNull;

import net.shopxx.entity.biz.Merchant;

/**
 * 社区entitity
 * 
 * @author linajsh
 */
@Entity
@Table(name = "xx_estate_community")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_community_sequence")
public class Community extends BaseEntity {

    private static final long serialVersionUID = 1L;
    private String            name;
    private double            longitude;
    private double            latitude;             // 纬度
    private double            radius;               // 社区覆盖半径
    private String            address;
    private String            introduction;
    private String            json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    private Area          area;    // 地区

    private Set<Merchant> merchant; // 商户

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Merchant> getMerchant() {
        return merchant;
    }

    public void setMerchant(Set<Merchant> merchant) {
        this.merchant = merchant;
    }

    private Set<ComplaintRep> complaintReps; // 投诉报修

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<ComplaintRep> getComplaintReps() {
        return complaintReps;
    }

    public void setComplaintReps(Set<ComplaintRep> complaintReps) {
        this.complaintReps = complaintReps;
    }

    private int stage; // 审核阶段或期间，一审或二审等；为空时，只有一审。1表示一审，2表示二审

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Date getAuditedDate() {
        return auditedDate;
    }

    public void setAuditedDate(Date auditedDate) {
        this.auditedDate = auditedDate;
    }

    public Date getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(Date submittedDate) {
        this.submittedDate = submittedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private String opinion;      // 审核意见
    private Date   auditedDate;  // 审核时间
    private Date   submittedDate; // 提交时间
    private int    status;       // 每一审核阶段的审核状态：1待审、2审核通过、3审核驳回、4草稿

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    private Set<Propertytocommunity> propertytocommunitys = new HashSet<Propertytocommunity>(); // 物业和小区的管理关系

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Propertytocommunity> getPropertytocommunitys() {
        return propertytocommunitys;
    }

    public void setPropertytocommunitys(Set<Propertytocommunity> propertytocommunitys) {
        this.propertytocommunitys = propertytocommunitys;
    }

    private Set<Communitytostaff> communitytostaffs = new HashSet<Communitytostaff>();// 小区和物业人员的关系

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Communitytostaff> getCommunitytostaffs() {
        return communitytostaffs;
    }

    public void setCommunitytostaffs(Set<Communitytostaff> communitytostaffs) {
        this.communitytostaffs = communitytostaffs;
    }

    private Set<Notice> notices = new HashSet<Notice>();// 小区和公告的关系

    @OneToMany(mappedBy = "community", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Notice> getNotices() {
        return notices;
    }

    public void setNotices(Set<Notice> notices) {
        this.notices = notices;
    }

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
}
