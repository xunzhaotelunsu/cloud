package com.baosight.cloud.service.account.persist.mapper;

import com.baosight.cloud.service.account.persist.entity.Login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by yang on 2017/6/5.
 */
@Mapper
public interface LoginMapper {

    Login getLoginByLoginName(@Param("loginName") String loginName);

    Login getLoginByUserIdAndType(@Param("userId") String userId, @Param("type") String type);

    int getLoginNameCount(@Param("loginName") String loginName);

    int insertLogin(Login login);

    int updateLogin(@Param("userId") String userId, @Param("loginName") String loginName, @Param("type") String type);
}
