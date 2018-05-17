package com.baosight.cloud.service.account.controller;

import com.baosight.cloud.service.account.controller.dto.TryLoginResult;
import com.baosight.cloud.service.account.model.AccountModel;
import com.baosight.cloud.service.account.model.LoginModel;
import com.baosight.cloud.service.account.persist.entity.Account;
import com.baosight.cloud.service.account.util.SHA256Util;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yang on 2018/3/21.
 */
@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    LoginModel loginModel;

    @Autowired
    AccountModel accountModel;

    @ApiOperation(value = "登录校验")
    @PostMapping(value = "/tryLogin")
    public TryLoginResult tryLogin(@RequestParam("loginName") String loginName,
                                   @RequestParam("password") String password){
        TryLoginResult tryLoginResult = new TryLoginResult();
        String userId = loginModel.getLogin(loginName);
        if(!StringUtils.hasText(userId)){
            tryLoginResult.setStatus(false);
            tryLoginResult.setMsg("账户不存在");
            return tryLoginResult;
        }
        Account account = accountModel.getAccount(userId);
        String encryptedPassword = SHA256Util.encode(password);
        if(!encryptedPassword.equals(account.getEncryptedPassword())){
            tryLoginResult.setStatus(false);
            tryLoginResult.setMsg("密码错误");
            return tryLoginResult;
        }
        tryLoginResult.setStatus(true);
        tryLoginResult.setUserId(userId);
        return tryLoginResult;
    }
}
