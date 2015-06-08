/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.xmsoft.cn
 * License: http://www.xmsoft.cn/license
 */
package net.shopxx.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity - 小区公告
 * 
 * @author fangym
 * @version 1.0
 */
@Entity
@Table(name = "xx_estate_notice")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "xx_estate_notice_sequence")
public class Notice extends OrderEntity {

    private static final long serialVersionUID = 3153143071428001149L;

    private String            type;

    private Long              priority;                                // 优先级

    private String            title;

    private String            content;
    
    private Date              releaseTime;                             //发布时间

    private Community         community;                               // 小区

    private String            communitys;                              //发送的小区

    private Long              isFixedTime;

    private Long              isSend;                                  //是否推送
    
    private Date              sendTime;

    private String            attachmentUrl;

    private String            user;

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Long getIsFixedTime() {
        return isFixedTime;
    }

    public void setIsFixedTime(Long isFixedTime) {
        this.isFixedTime = isFixedTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    
    public String getCommunitys() {
        return communitys;
    }

    
    public void setCommunitys(String communitys) {
        this.communitys = communitys;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public void setAttachmentUrl(String attachmentUrl) {
        this.attachmentUrl = attachmentUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

 
    public Date getReleaseTime() {
        return releaseTime;
    }

    
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    
    public Long getIsSend() {
        return isSend;
    }

    
    public void setIsSend(Long isSend) {
        this.isSend = isSend;
    }

    
    public String getType() {
        return type;
    }

    
    public void setType(String type) {
        this.type = type;
    }

}
