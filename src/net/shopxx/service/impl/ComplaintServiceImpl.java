package net.shopxx.service.impl;
import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.ComplaintDao;
import net.shopxx.dao.ComplaintRepImgDao;
import net.shopxx.dao.ComplaintRepLogDao;
import net.shopxx.entity.ComplaintRep;
import net.shopxx.entity.ComplaintRepAnnex;
import net.shopxx.entity.ComplaintRepLog;
import net.shopxx.service.ComplaintService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service -社区
 * 
 * @author chenshw
 * 
 */
@Service("complaintServiceImpl")
public class ComplaintServiceImpl extends BaseServiceImpl<ComplaintRep, Long> implements ComplaintService {
	@Resource(name = "complaintRepLogDaoImpl")
	private ComplaintRepLogDao complaintRepLogDao;
	
	@Resource(name = "complaintRepImgDaoImpl")
	private ComplaintRepImgDao complaintRepImgDao;
	
	@Resource(name = "complaintDaoImpl")
	private ComplaintDao complaintDao;

	@Resource(name = "complaintDaoImpl")
	public void setBaseDao(ComplaintDao complaintDao) {
		super.setBaseDao(complaintDao);
	}

	@Override	
	public List<ComplaintRepAnnex> getImgList() {
		return complaintRepImgDao.findList(null, null, null, null);
	}
	
	@Override
	public List<ComplaintRepLog> getLogList() {
		return complaintRepLogDao.findList(null, null, null, null);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void save(ComplaintRep complaint) {
		super.save(complaint);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public ComplaintRep update(ComplaintRep complaint) {
		return super.update(complaint);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public ComplaintRep update(ComplaintRep complaint, String... ignoreProperties) {
		return super.update(complaint, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void delete(ComplaintRep complaint) {
		super.delete(complaint);
	}
	
	
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void saveImg(ComplaintRepAnnex entity) {
		complaintRepImgDao.persist(entity);				
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void saveLog(ComplaintRepLog entity) {
		complaintRepLogDao.persist(entity);		
	}
}