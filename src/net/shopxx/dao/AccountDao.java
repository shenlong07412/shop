package net.shopxx.dao;

import net.shopxx.entity.Admin;
import net.shopxx.entity.biz.Account;


/**
 * Dao - 账号
 * 
 * @author lianjsh
 * 
 */
public interface AccountDao extends BaseDao<Account, Long> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 根据用户名查找账号
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 账号，若不存在则返回null
	 */
	Account findByUsername(String username);
}