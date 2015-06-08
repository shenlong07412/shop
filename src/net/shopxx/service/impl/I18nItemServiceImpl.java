package net.shopxx.service.impl;
import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.I18nItemDao;
import net.shopxx.entity.I18nItem;
import net.shopxx.service.I18nItemService;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service -国际化项目
 * 
 * @author lianjsh
 * 
 */
@Service("i18nItemServiceImpl")
public class I18nItemServiceImpl extends BaseServiceImpl<I18nItem, Long> implements I18nItemService {

	@Resource(name = "i18nItemDaoImpl")
	private I18nItemDao i18nItemDao;

	@Resource(name = "i18nItemDaoImpl")
	public void setBaseDao(I18nItemDao i18nItemDao) {
		super.setBaseDao(i18nItemDao);
	}

	@Override
	public List<I18nItem> getListByI18nCode(String i18nItem) {
		return i18nItemDao.getListByI18nCode(i18nItem);
	}

	@Override
	@Transactional
	public void save(I18nItem i18nItem) {
		super.save(i18nItem);
	}

	@Override
	@Transactional
	public I18nItem update(I18nItem i18nItem) {
		return super.update(i18nItem);
	}

	@Override
	@Transactional
	public I18nItem update(I18nItem i18nItem, String... ignoreProperties) {
		return super.update(i18nItem, ignoreProperties);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	public void delete(I18nItem i18nItem) {
		super.delete(i18nItem);
	}

    @Override
    public I18nItem getByLocalCode(String localCode) {
 
        return i18nItemDao.getByLocalCode(localCode);
    }

	@Override
	public boolean localCodeExists(String localCode) {
		
		return i18nItemDao.localCodeExists(localCode);
	}
	
	
}