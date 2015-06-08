package net.shopxx.service;

import net.shopxx.entity.Sensitivity;

public interface SensitivityService extends BaseService<Sensitivity, Long>{
	
	/**
	 * 替换敏感词
	 * @param source 要替换的字符串
	 * @param to     要替换成的目标字符
	 * @return
	 */
	public String replaceSensitivity(String source,String to);

}
