package com.baosight.cloud.service.account.controller;

import com.baosight.cloud.service.account.controller.dto.ResetPasswordInfo;
import com.baosight.cloud.service.account.controller.dto.ResetPasswordResult;
import com.baosight.cloud.service.account.model.AccountModel;
import com.baosight.cloud.service.account.persist.entity.Account;
import com.baosight.cloud.service.account.util.SHA256Util;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by yang on 2018/3/21.
 */
@RestController
public class AccountController {

    @Autowired
    AccountModel accountModel;

    @ApiOperation(value = "获取当前用户基本信息")
    @GetMapping(value = "/accountInfo")
    public Account getCurrentAccountInfo(HttpServletRequest request){
        String userId = request.getHeader("userId");
        return accountModel.getAccount(userId);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/resetPassword")
    @Transactional
    public ResetPasswordResult resetPassword(@RequestBody @Valid ResetPasswordInfo resetPasswordInfo,
                                             BindingResult bindingResult,
                                             HttpServletRequest request){
        ResetPasswordResult resetPasswordResult = new ResetPasswordResult();
        if(bindingResult.hasErrors()){
            resetPasswordResult.setReset(false);
            resetPasswordResult.setMsg(bindingResult.getFieldError().getDefaultMessage());
            return resetPasswordResult;
        }
        String userId = request.getHeader("userId");
        Account account = accountModel.getAccount(userId);
        String encryptedOldPassword = SHA256Util.encode(resetPasswordInfo.getOldPassword());
        if(encryptedOldPassword.equals(account.getEncryptedPassword())){
            String encryptedNewPassword = SHA256Util.encode(resetPasswordInfo.getNewPassword());
            accountModel.resetPassword(userId, encryptedNewPassword);
            resetPasswordResult.setReset(true);
        }else{
            resetPasswordResult.setReset(false);
            resetPasswordResult.setMsg("旧密码错误");
        }
        return resetPasswordResult;
    }
}
