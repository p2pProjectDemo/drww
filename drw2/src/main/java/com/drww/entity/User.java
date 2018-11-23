package com.drww.entity;

import java.io.Serializable;

/**
 * className:User
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-23 17:30
 */
public class User  implements Serializable {
    private int userId;
    private String userName;
    private String PWD;
    private String salt;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
