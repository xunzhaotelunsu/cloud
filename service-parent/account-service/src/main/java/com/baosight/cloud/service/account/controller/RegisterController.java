package com.baosight.cloud.service.account.controller;

import com.baosight.cloud.service.account.constants.AccountConstants;
import com.baosight.cloud.service.account.controller.dto.RegisterInfo;
import com.baosight.cloud.service.account.controller.dto.RegisterResult;
import com.baosight.cloud.service.account.model.AccountModel;
import com.baosight.cloud.service.account.model.LoginModel;
import com.baosight.cloud.service.account.persist.entity.Account;
import com.baosight.cloud.service.account.persist.entity.Login;
import com.baosight.cloud.service.account.util.SHA256Util;
import com.baosight.cloud.service.account.util.SMSVerifyUtil;
import com.baosight.cloud.tools.redis.StringRedisOperation;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by yang on 2018/3/20.
 */
@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    @Autowired
    AccountModel accountModel;

    @Autowired
    LoginModel loginModel;

    @Autowired
    StringRedisOperation stringRedisOperation;

    @Autowired
    SMSVerifyUtil smsVerifyUtil;

    private static final String USER_ID_KEY = "user_id";

    @ApiOperation(value = "账户注册")
    @PostMapping(value = "/doRegister")
    @Transactional
    public RegisterResult register(@RequestBody @Valid RegisterInfo registerInfo, BindingResult errors){
        RegisterResult registerResult =  new RegisterResult();
        if(errors.hasErrors()){
            registerResult.setRegistered(false);
            registerResult.setMsg(errors.getFieldError().getDefaultMessage());
            return registerResult;
        }
        if(!loginModel.checkLoginName(registerInfo.getMobile())){
            registerResult.setRegistered(false);
            registerResult.setMsg("手机号已被注册");
            return registerResult;
        }
        if(!loginModel.checkLoginName(registerInfo.getUserName())){
            registerResult.setRegistered(false);
            registerResult.setMsg("用户名已被注册");
            return registerResult;
        }
        if(!smsVerifyUtil.checkVerifyCode(registerInfo.getMobile(), "register", registerInfo.getInputVerifyCode())){
            registerResult.setRegistered(false);
            registerResult.setMsg("短信验证码错误");
            return registerResult;
        }
        //插入账户
        String userId = generateUserId();
        Account account = new Account();
        account.setUserId(userId);
        account.setUserName(registerInfo.getUserName());
        account.setMobile(registerInfo.getMobile());
        account.setEncryptedPassword(SHA256Util.encode(registerInfo.getPassword()));
        account.setStatus(AccountConstants.ACCOUNT_STATUS_ACTIVE);
        account.setCreateDate(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        accountModel.addAccount(account);
        //插入登录信息
        Login login = new Login();
        login.setUserId(userId);
        login.setLoginName(userId);
        login.setType(AccountConstants.LOGIN_NAME_TYPE_ID);
        loginModel.addLogin(login);
        login.setLoginName(registerInfo.getUserName());
        login.setType(AccountConstants.LOGIN_NAME_TYPE_NAME);
        loginModel.addLogin(login);
        login.setLoginName(registerInfo.getMobile());
        login.setType(AccountConstants.LOGIN_NAME_TYPE_MOBILE);
        loginModel.addLogin(login);
        registerResult.setRegistered(true);
        registerResult.setUserId(userId);
        return registerResult;
    }

    @ApiOperation(value = "校验登录名是否被占用，适用于手机号、登录名")
    @GetMapping(value = "/check")
    public boolean checkLoginName(@RequestParam("loginName")String loginName){
        return loginModel.checkLoginName(loginName);
    }

    @ApiOperation(value = "发送验证码")
    @PostMapping(value = "/smsVerify")
    public boolean smsVerify(@RequestParam("mobile") String mobile){
        smsVerifyUtil.sendVerifyCode(mobile, "register");
        return true;
    }

    private String generateUserId(){
        long result = stringRedisOperation.incr(USER_ID_KEY);
        return String.valueOf(result);
    }
}
