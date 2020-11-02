package com.test.transaction.dao;

import com.test.transacton.entity.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LogDao {

    @Autowired
    public JdbcTemplate jdbcTemplate;


    public void insertLog(Log log){
        String sql = "INSERT INTO `log` (id,method_name,args,create_time)VALUES(?,?,?,?)";
        jdbcTemplate.update(sql,log.getId(),log.getMethodName(),log.getArgs(),log.getCreateTime());
    }
}
