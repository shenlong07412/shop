package net.shopxx.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 栏目entity(对商户的分类）
 * @author lianjsh
 *
 */
@Entity
@Table(name = "xx_channel")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_channel_sequence")
public class Channel extends BaseEntity {


	private static final long serialVersionUID = 1L;
	private Long parentId;  // 父栏目ID, 为空时即顶级栏目
	private String name;  // 栏目名称
	private String icon;  // 栏目的透明图标的地址
	private String color;   // 图标的底色
	private int weight;  // 同一级栏目的序号
	private Date createdDate;  // 栏目的创建或更新的时间
	private Date updatedDate;   // 栏目更新的时间
	
	public Long getParentId() {
		return parentId;
	}
	public String getName() {
		return name;
	}
	public String getIcon() {
		return icon;
	}
	public String getColor() {
		return color;
	}
	public int getWeight() {
		return weight;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
}
