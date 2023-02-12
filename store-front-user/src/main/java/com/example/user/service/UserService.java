package com.example.user.service;

import com.example.param.UserCheckParam;
import com.example.utils.R;

public interface UserService {
    /**
     *  检查账号是否可用业务
     * @param userCheckParam 账号参数 已经校验完毕
     * @return 检查结果
     */
    R check(UserCheckParam userCheckParam);
}
