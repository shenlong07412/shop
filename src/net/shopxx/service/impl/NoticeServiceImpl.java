package net.shopxx.service.impl;

import javax.annotation.Resource;

import net.shopxx.dao.NoticeDao;
import net.shopxx.entity.Notice;
import net.shopxx.service.NoticeService;

import org.springframework.stereotype.Service;

/**
 * Service - 公告
 * 
 * @author fangym
 * @version 1.0
 */
@Service("noticeServiceImpl")
public class NoticeServiceImpl extends BaseServiceImpl<Notice, Long> implements NoticeService {


    @Resource(name = "noticeDaoImpl")
    public void setBaseDao(NoticeDao noticeDao) {
        super.setBaseDao(noticeDao);
    } 
    
    
}
