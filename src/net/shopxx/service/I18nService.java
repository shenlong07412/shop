package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.I18n;
/**
 * Service - 国际化项目
 * 
 * @author lianjsh
 * 
 */
public interface I18nService extends BaseService<I18n, Long> {
	/**
	 * 取出所有的国际化代码
	 * @return 国际化代码list
	 */
	 public List<I18n> findAll();
	
	/**
	 * 	判断国际化项目是否存在
	 * @param code
	 * 		国际化代码
	 * @return
	 */
	 public boolean i18nCodeExists(String code);
	 /**
		 * 	根据i18nCode获取国际化代码
		 * @param i18nCode
		 * 		国际化代码
		 * @return
		 */
	 public I18n findByi18nCode(String i18nCode); 

}