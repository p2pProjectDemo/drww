package com.drww.entity;

import java.util.List;

/**
 * className:Permission
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-13 20:51
 */
public class Permission {
    private Integer id;
    private Integer pId;
    private String name;
    private String URL;
    private Integer status;
    private List<Permission> children;

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public List<Permission> getChildren() {
        return children;
    }

    public void setChildren(List<Permission> children) {
        this.children = children;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
