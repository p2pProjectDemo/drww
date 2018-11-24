package com.drww.service.customer;

import com.drww.entity.Customer;

/**
 * className:InformationService
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-24 14:44
 */
public interface InformationService {
    /**
     * 查询用户手机号是否绑定
     * @param userName
     * @return
     */
    Customer getTelephoneByUserName(String userName);
}
