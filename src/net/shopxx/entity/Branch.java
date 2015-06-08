/*
 * Copyright 2014 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// Created on 2014年7月4日
// $Id$

package net.shopxx.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 组织机构
 * 
 * @author czllfy
 */
@Entity
@Table(name = "xx_sys_branch")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_sys_branch_sequence")
public class Branch extends OrderEntity {

    /**
     * 
     */
    private static final long serialVersionUID = 1533353185666134017L;

    /**
     * 上级组织机构
     */
    protected Branch          parent;
    /**
     * 机构编码
     */
    protected String          branchCode;

    /**
     * 机构类型(1、表示物业公司、2表示)
     */
    protected String          type;

    /**
     * 名称
     */
    protected String          name;

    /**
     * 树状级别
     */
    protected Long            treeLevel;

    /**
     * 是否叶子结点
     */
    protected String          isLeaf;

    /**
     * 联系电话
     */
    protected String          phone;

    /**
     * 传真
     */
    protected String          fax;

    /**
     * 机构主管
     */
    protected String          manager;

    /**
     * 备用属性1
     */
    protected String          prop1;

    /**
     * 备用属性2
     */
    protected String          prop2;

    /**
     * 备用属性3
     */
    protected String          prop3;
    /**
     * 备用属性4
     */
    protected String          prop4;
    /**
     * 备注
     */
    protected String          memo;

    /**
     * 下级组织机构
     */
    protected Set<Branch>     children         = new HashSet<Branch>();

    /**
     * @return the parent
     */
    @ManyToOne(fetch = FetchType.LAZY)
    public Branch getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Branch parent) {
        this.parent = parent;
    }

    /**
     * @return the branchCode
     */
    @Column(nullable = false, updatable = false, unique = true, length = 12)
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * @param branchCode the branchCode to set
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * @return the type
     */
    @Column(length = 2)
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    @NotEmpty
    @Length(max = 100)
    @JsonProperty
    @Column(nullable = false, length = 100)
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the treeLevel
     */
    @NotEmpty
    @Length(max = 2)
    @Column(nullable = false, length = 2)
    public Long getTreeLevel() {
        return treeLevel;
    }

    /**
     * @param treeLevel the treeLevel to set
     */
    public void setTreeLevel(Long treeLevel) {
        this.treeLevel = treeLevel;
    }

    /**
     * @return the isLeaf
     */
    public String getIsLeaf() {
        return isLeaf;
    }

    /**
     * @param isLeaf the isLeaf to set
     */
    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * @return the phone
     */
    @NotEmpty
    @Length(max = 100)
    @Column(nullable = false, length = 100)
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the fax
     */
    @NotEmpty
    @Length(max = 100)
    @Column(nullable = false, length = 100)
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the manager
     */
    @NotEmpty
    @Length(max = 100)
    @Column(nullable = false, length = 100)
    public String getManager() {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(String manager) {
        this.manager = manager;
    }

    /**
     * @return the prop1
     */
    public String getProp1() {
        return prop1;
    }

    /**
     * @param prop1 the prop1 to set
     */
    public void setProp1(String prop1) {
        this.prop1 = prop1;
    }

    /**
     * @return the prop2
     */
    public String getProp2() {
        return prop2;
    }

    /**
     * @param prop2 the prop2 to set
     */
    public void setProp2(String prop2) {
        this.prop2 = prop2;
    }

    /**
     * @return the prop3
     */
    public String getProp3() {
        return prop3;
    }

    /**
     * @param prop3 the prop3 to set
     */
    public void setProp3(String prop3) {
        this.prop3 = prop3;
    }

    /**
     * @return the prop4
     */
    public String getProp4() {
        return prop4;
    }

    /**
     * @param prop4 the prop4 to set
     */
    public void setProp4(String prop4) {
        this.prop4 = prop4;
    }

    /**
     * @return the memo
     */
    @NotEmpty
    @Length(max = 1024)
    @Column(nullable = false, length = 1024)
    public String getMemo() {
        return memo;
    }

    /**
     * @param memo the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * @return the children
     */
    @JsonProperty
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    @OrderBy("order asc")
    public Set<Branch> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(Set<Branch> children) {
        this.children = children;
    }

}
