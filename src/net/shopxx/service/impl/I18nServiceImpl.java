package net.shopxx.service.impl;
import java.util.List;

import javax.annotation.Resource;

import net.shopxx.dao.I18nDao;
import net.shopxx.entity.I18n;
import net.shopxx.service.I18nService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service -国际化项目
 * 
 * @author lianjsh
 * 
 */
@Service("i18nServiceImpl")
public class I18nServiceImpl extends BaseServiceImpl<I18n, Long> implements I18nService {

	@Resource(name = "i18nDaoImpl")
	private I18nDao i18nDao;

	@Resource(name = "i18nDaoImpl")
	public void setBaseDao(I18nDao i18nDao) {
		super.setBaseDao(i18nDao);
	}

	@Override
	public List<I18n> findAll() {
		return i18nDao.findAll();
	}
	

	@Override
	@Transactional
	public void save(I18n i18n) {
		super.save(i18n);
	}

	@Override
	@Transactional
	public I18n update(I18n i18n) {
		return super.update(i18n);
	}

	@Override
	@Transactional
	public I18n update(I18n i18n, String... ignoreProperties) {
		return super.update(i18n, ignoreProperties);
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
	public void delete(I18n i18n) {
		super.delete(i18n);
	}

    public I18n findByi18nCode(String i18nCode) {
 
        return i18nDao.findByi18nCode(i18nCode);
    }

	public boolean i18nCodeExists(String i18nCode) {
		
		return i18nDao.i18nCodeExists(i18nCode);
	}
	
	
}