package com.drww.service.function;

import com.drww.dao.function.FunctionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * className:FunctionServiceImpl
 * discriptoin:
 * author:丁启斌
 * createTime:2018-11-23 11:53
 */
@Service
public class FunctionServiceImpl implements FunctionService {
        @Autowired
        private FunctionDao functionDao;

    @Override
    public List<Map> getList() {
        List<Map> list = functionDao.getList();
        return list;
    }

    @Override
    public List<Map> getListByPid(Integer Pid) {
        List<Map> childrenList = functionDao.getListByPid(Pid);

        return childrenList;
    }
}
