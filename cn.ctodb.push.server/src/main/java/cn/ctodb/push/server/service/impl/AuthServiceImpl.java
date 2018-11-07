package cn.ctodb.push.server.service.impl;

import cn.ctodb.push.server.service.AuthService;
import org.springframework.stereotype.Service;

/**
 * All rights Reserved, Designed By www.ctodb.cn
 *
 * @version V1.0
 * @author: lichaohn@163.com
 * @date: 2018-11-07 14:34
 * @Copyright: 2018 www.ctodb.cn All rights reserved.
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean check(String uid, String token) {
        return true;
    }
}
