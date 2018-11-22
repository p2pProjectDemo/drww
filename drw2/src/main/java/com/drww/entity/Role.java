package com.drww.entity;

import java.util.List;

/**
 * className:Role
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-22 19:15
 */
public class Role {
    private Integer roleId;
    private String roleName;
    private String remarks;
    private List<Permission> permissionList;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
