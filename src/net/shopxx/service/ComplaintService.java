package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.ComplaintRep;
import net.shopxx.entity.ComplaintRepAnnex;
import net.shopxx.entity.ComplaintRepLog;
/**
 * Service - 社区
 * 
 * @author chenshw
 * 
 */
public interface ComplaintService extends BaseService<ComplaintRep, Long> {

	public List<ComplaintRepAnnex> getImgList();

	public List<ComplaintRepLog> getLogList();
	
	public void saveImg(ComplaintRepAnnex entity);
	
	public void saveLog(ComplaintRepLog entity);
}