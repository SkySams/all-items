package org.example.business.impl;

import org.example.business.TransactionalService;
import org.example.dao.UserDao;
import org.example.dao.UserLogDao;
import org.example.entity.UserLog;
import org.example.entity.eo.User;
import org.example.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author: zyh
 * @date: 2022/10/28
 */
@Service
public class TransactionalServiceImpl implements TransactionalService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserLogDao userLogDao;



    /**
     * 测试事务在private修饰下，事务是否失效
     * 事务需要在被调用上使用，否则失效
     *@Transactional不在 Service层调用方法之上，事务失效 (方法的内部调用。)
     */

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void privateTrasational() {
        this.testPrivate();
    }

    /**
     * 当事务方法被private修饰之后，事务失效
     */
//    @Transactional(rollbackFor = Exception.class)
    private void testPrivate(){
        User user = userDao.selectById(1);
        user.setAge(23);
        userDao.updateById(user);
        System.out.println(2/0);

    }

    /**
     * 使用默认的修饰方法
     * 事务失效
     */
    @Transactional(rollbackFor = Exception.class)
    void testDefault(){
        test(2,40);
    }

    @Override
    public void testPublice(){
//        test(2,90);
        User user = userDao.selectById(2);
        user.setAge(100);
        userDao.updateById(user);
        throw new AssertionError();
    }

    @Override
    public void optionUser() {
        optionalUser(4);
        optionalUserLog(1);

    }

    /**
     * Methods annotated with '@Transactional' must be overridable
     * 是否 final修饰方法，事务失效
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public final void testFinalTrasational() {
        test(5,100);
    }

    /**
     * 重写 事务 rollbackFor 异常, 事务失效
     * 有待验证
     */
    @Transactional(rollbackFor = BusinessException.class)
    @Override
    public void overrideException()  {
        this.test(4,130);
    }

    /**
     * 异常被开发者吞并了， 事务异常
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void annexation() {
        try{
            this.test(4,21);
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("异常被开发者补获了，事物无法回滚");
        }
    }

    public void optionalUser(Integer id){
        User user = userDao.selectById(id);
        user.setEmail("11526108@qq.com");
        userDao.updateById(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void optionalUserLog(Integer id){
       UserLog userLog = userLogDao.selectById(id);
       userLog.setUserId(20);
       userLogDao.updateById(userLog);
       System.out.println(2/0);
    }


    public void test(Integer id,Integer age){
        User user = userDao.selectById(id);
        user.setAge(age);
        userDao.updateById(user);
        System.out.println(2/0);
    }
}
