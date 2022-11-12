package org.example.thread.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

/**
 * @author: zyh
 * @date: 2022/11/1
 */
public class ThreadSecurity {

    /**
     满足三个条件就会有线程安全问题
     1、多线程并发
     2、有数据共享
     3、共享数据有更改

     解决方案: 线程同步机制

     线程不是并发，必须排队执行，效率低
     */

    /**
     * 不使用线程同步机制：两个线程同时对账户进行取款，存在线程安全问题。
     */
    @Test
    public void one() throws InterruptedException {
        Account account = new Account(1,30000D);
        AccountThread accountThread = new AccountThread(account);

        Thread thread1 = new Thread(accountThread);
        thread1.start();

        Thread thread2 = new Thread(accountThread);
        thread2.start();

        thread2.join();
    }


}

class AccountThread implements Runnable {

    private Account account;
    public AccountThread(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        Double money = 5000d;
        Double balance = account.withdraw(money);
        System.out.println(Thread.currentThread().getName() + " 取款 " + money + "元！余额为：" + balance);
    }
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class Account{

    private Integer id;
    private Double balance;

    public Double withdraw(Double amount){
        return balance = balance - amount;
    }
}
