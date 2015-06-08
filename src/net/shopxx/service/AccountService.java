package net.shopxx.service;

import java.util.List;

import net.shopxx.entity.biz.Account;

/**
 * Service - 账号
 * 
 * @author lianjsh
 */
public interface AccountService extends BaseService<Account, Long> {

    /**
     * 判断账号是否存在
     * 
     * @param username 用户名(忽略大小写)
     * @return 用户名是否存在
     */
    boolean usernameExists(String username);

    /**
     * 根据用户名查找账号
     * 
     * @param username 用户名(忽略大小写)
     * @return 账号，若不存在则返回null
     */
    Account findByUsername(String username);

    /**
     * 判断账号是否登录
     * 
     * @return 账号是否登录
     */
    boolean AccountIsLogin();

    /**
     * 根据ID查找权限
     * 
     * @param id ID
     * @return 权限,若不存在则返回null
     */
    List<String> findAuthorities(Long id);

    /**
     * 获取当前登录账号
     * 
     * @return 当前登录账号,若不存在则返回null
     */
    Account getCurrent();

    /**
     * 获取当前登录账号用户名
     * 
     * @return 当前登录账号用户名,若不存在则返回null
     */
    String getCurrentUsername();

}
