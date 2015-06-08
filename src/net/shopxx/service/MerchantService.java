package net.shopxx.service;

import java.util.List;

import net.shopxx.Filter;
import net.shopxx.Page;
import net.shopxx.Pageable;
import net.shopxx.entity.biz.Merchant;
/**
 * Service - 商户
 * 
 * @author lianjsh
 * 
 */
public interface MerchantService extends BaseService<Merchant, Long> {

	/**
	 * 判断商户是否存在
	 * 
	 * @param username
	 *            商户名(忽略大小写)
	 * @return 商户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 根据商户名查找商户
	 * 
	 * @param username
	 *            商户名(忽略大小写)
	 * @return 商户，若不存在则返回null
	 */
	Merchant findByUsername(String username);
	/**
	 * 查找商户
	 * 
	 * @param merchant
	 *            商户
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @return 商户
	 */
	List<Merchant> findList(Merchant merchant, Integer count, List<Filter> filters, List<net.shopxx.Order> orders);

	/**
	 * 查找商户分页
	 * 
	 * @param merchant
	 *            商户
	 * @param pageable
	 *            分页信息
	 * @return 商户分页
	 */
	Page<Merchant> findPage(Merchant merchant, Pageable pageable);

	/**
	 * 查找商户分页
	 * 
	 * @param checkStatus
	 *            审核状态
	 * @param recommendStatus
	 *            推荐状态
	 * @param pageable
	 *            分页信息
	 * @return 商户分页
	 */
	Page<Merchant> findPage(Integer checkStus, Integer recommendStatus, Pageable pageable);
	
	/**
	 * 当前商户
	 * @return
	 */
	Merchant getCurrent();


}