package test;

import com.test.transaction.config.TxConfig;
import com.test.transaction.service.LogService;
import com.test.transacton.entity.Log;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;


public class Mytest {

    @Test
    public void testInsertLogOfNoTransaction() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
        LogService logService = (LogService) applicationContext.getBean("logService");
        Log log = new Log();
        log.setId(1);
        log.setMethodName("insert");
        log.setArgs("id:" + log.getId() + ",method_name:" + log.getMethodName() + ",args:" + log.getArgs());
        log.setCreateTime(new Date());
        logService.insertLog(log);

    }

    @Test
    public void testInsertLogOfTransaction() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TxConfig.class);
        LogService logService = (LogService) applicationContext.getBean("logService");
        Log log = new Log();
        log.setId(2);
        log.setMethodName("insert");
        log.setArgs("id:" + log.getId() + ",method_name:" + log.getMethodName() + ",args:" + log.getArgs());
        log.setCreateTime(new Date());
        logService.insertLogTransaction(log);
    }
}
