package com.baosight.cloud.service.account.model;

import com.baosight.cloud.service.account.persist.entity.Account;
import com.baosight.cloud.service.account.persist.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yang on 2018/3/20.
 */
@Component
public class AccountModel {

    @Autowired
    AccountMapper accountMapper;

    /**
     * 获取账户信息
     * @param userId
     * @returnId
     */
    public Account getAccount(String userId){
        return accountMapper.getAccount(userId);
    }

    /**
     * 新增账户
     * @param account
     */
    public int addAccount(Account account){
        return accountMapper.insertAccount(account);
    }

    /**
     * 重置密码
     * @param userId
     * @param newPassword
     * @return
     */
    public int resetPassword(String userId,String newPassword){
        return accountMapper.updatePassword(userId, newPassword);
    }
}
