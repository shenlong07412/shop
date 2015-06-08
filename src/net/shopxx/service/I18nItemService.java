package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.I18nItem;
/**
 * Service - 国际化项目
 * 
 * @author lianjsh
 * 
 */
public interface I18nItemService extends BaseService<I18nItem, Long> {
	/**
	 * 根据 国际化代码获取国际化项目
	 * @param i18nCode
	 * 		  国际化代码
	 * @return 国际化项目list
	 */
	List<I18nItem> getListByI18nCode(String i18nCode);
	
	/**
	 * 	根据本地化代码获取国际化项目
	 * @param localCode
	 * 			本地化代码
	 * 
	 * @return
	 */
	I18nItem getByLocalCode(String localCode);
	
	/**
	 * 判断本地化代码是否存在
	 * 
	 * @param localCode
	 *            本地化代码(忽略大小写)
	 * @return 本地化代码是否存在
	 */
	boolean localCodeExists(String localCode);


}