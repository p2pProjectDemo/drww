package com.drww.dao.login;

import com.drww.entity.Role;
import com.drww.entity.User;

import java.util.List;

/**
 * className:LoginDad
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-23 17:34
 */
public interface UserDao {

   User findUserByName(String userName);

    List<Role> findById(Integer userId);
}
