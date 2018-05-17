package com.baosight.cloud.service.account.model;

import com.baosight.cloud.service.account.persist.entity.Login;
import com.baosight.cloud.service.account.persist.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * Created by yang on 2018/3/20.
 */
@Component
public class LoginModel {

    @Autowired
    LoginMapper loginMapper;

    /**
     * 获取登录userId
     *
     * @param loginName
     * @return
     */
    public String getLogin(String loginName) {
        Login login = loginMapper.getLoginByLoginName(loginName);
        if(ObjectUtils.isEmpty(login)){
            return "";
        }else{
            return login.getUserId();
        }
    }

    /**
     * 检查登录信息是否已被注册
     *
     * @param loginName
     * @return
     */
    public boolean checkLoginName(String loginName) {
        if (StringUtils.hasText(loginName)) {
            int count = loginMapper.getLoginNameCount(loginName);
            if (count > 0) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public int addLogin(Login login){
        return loginMapper.insertLogin(login);
    }

    /**
     * 设置登录信息
     *
     * @param userId
     * @param loginName
     * @param type
     */
    public boolean setLogin(String userId, String loginName, String type) {
        if (!checkLoginName(loginName)) {
            return false;
        }
        Login login = loginMapper.getLoginByUserIdAndType(userId, type);
        if (ObjectUtils.isEmpty(login)) {
            //新增
            login.setUserId(userId);
            login.setLoginName(loginName);
            login.setType(type);
            loginMapper.insertLogin(login);
        } else {
            //更新
            loginMapper.updateLogin(userId, loginName, type);
        }
        return true;
    }

}
