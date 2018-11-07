package cn.ctodb.push.server.service;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @date: 2018-11-07 14:24
 * @Copyright: 2018 www.ctodb.cn All rights reserved.
 */
public interface AuthService {

    /**
     * 根据用户ID和TOKEN，判断是否登录
     *
     * @param uid
     * @param token
     * @return
     */
    boolean check(String uid, String token);

}
