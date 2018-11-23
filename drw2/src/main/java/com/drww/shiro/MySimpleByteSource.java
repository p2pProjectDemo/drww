package com.drww.shiro;

import org.apache.shiro.util.SimpleByteSource;

import java.io.Serializable;

/**
 * className:MySimpleByteSource
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-20 20:19
 */
public class MySimpleByteSource  extends SimpleByteSource implements Serializable {
    private static final long serialVersionUID = 5528101080905698238L;
    public MySimpleByteSource(byte[] bytes) {
        super(bytes);
        // TODO 自动生成的构造函数存根
        }


}
