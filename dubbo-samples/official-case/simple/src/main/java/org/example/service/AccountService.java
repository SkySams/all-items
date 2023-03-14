package org.example.service;

/**
 * @author: zyh
 * @date: 2023/3/14
 */
public interface AccountService {

    /**
     * 余额扣款
     *
     * @param userId 用户ID
     * @param money  扣款金额
     */
    void debit(String userId, int money);

}
