package com.drww.service.login;

import com.drww.entity.Role;
import com.drww.entity.User;

import java.util.List;

/**
 * className:LoginService
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-23 17:35
 */

public interface UserService {
    User findUserByName(String userName);
    List<Role> findById(Integer userId);
}
