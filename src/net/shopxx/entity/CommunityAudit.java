package net.shopxx.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "xx_community_audit")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_community_audit_sequence")
public class CommunityAudit extends BaseEntity {

	private static final long serialVersionUID = -3362702541360566651L;

	private Long CommunityId;;
	private int stage;  // 审核阶段或期间，一审或二审等；为空时，只有一审。1表示一审，2表示二审
	private String opinion;  // 审核意见
	private Date auditedDate;  // 审核时间
	private Date submittedDate;  // 提交时间
	private int status;  // 每一审核阶段的审核状态：1待审、2审核通过、3审核驳回、4草稿
	
	public Long getCommunityId() {
		return CommunityId;
	}
	public void setCommunityId(Long communityId) {
		CommunityId = communityId;
	}
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


}
