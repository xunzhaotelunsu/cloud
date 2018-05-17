package com.baosight.cloud.service.account.persist.mapper;

import com.baosight.cloud.service.account.persist.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by yang on 2017/6/5.
 */
@Mapper
public interface AccountMapper {

    Account getAccount(@Param("userId") String userId);

    int insertAccount(Account account);

    int updatePassword(@Param("userId") String userId, @Param("newPassword") String newPassword);

    List<Account> queryAccountList(Map<String, String> param);
}
