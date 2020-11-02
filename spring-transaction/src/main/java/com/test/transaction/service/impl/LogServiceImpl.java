package com.test.transaction.service.impl;

import com.test.transaction.dao.LogDao;
import com.test.transaction.service.LogService;
import com.test.transacton.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("logService")
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public void insertLog(Log log) {
        Optional.of("insert log  begin. id:" +log.getId()).ifPresent(System.out::println);
        logDao.insertLog(log);
        int result = 1/0;
        Optional.of("insert log  end. id:" +log.getId()).ifPresent(System.out::println);
    }

    /**
     * 跟@EnableTransactionManagement配合开启事务
     * @param log
     */
    @Transactional
    @Override
    public void insertLogTransaction(Log log) {
        Optional.of("insert log  begin. id:" +log.getId()).ifPresent(System.out::println);
        logDao.insertLog(log);
        int result = 1/0;
        Optional.of("insert log  end. id:" +log.getId()).ifPresent(System.out::println);
    }
}
