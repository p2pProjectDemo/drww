package com.drww.service.function;

import java.util.List;
import java.util.Map;

/**
 * className:FunctionService
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-23 11:49
 */
public interface FunctionService {

    /**
     * 得到所有的菜单列表
     * @return
     */
    List<Map> getList();
    /**
     * 根据父id查询
     * @param Pid
     * @return
     */
    List<Map> getListByPid(Integer Pid);
}
