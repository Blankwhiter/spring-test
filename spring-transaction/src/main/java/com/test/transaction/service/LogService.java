package com.test.transaction.service;

import com.test.transacton.entity.Log;

public interface LogService {
    void insertLog(Log log);
    void insertLogTransaction(Log log);
}
