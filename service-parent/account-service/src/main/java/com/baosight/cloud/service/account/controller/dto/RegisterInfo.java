package com.baosight.cloud.service.account.controller.dto;

import com.baosight.cloud.service.account.constants.AccountConstants;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by yang on 2018/3/21.
 */
@Data
public class RegisterInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    @Pattern(regexp = AccountConstants.USER_PATTERN, message = "用户名格式错误")
    String userName;

    @Pattern(regexp = AccountConstants.MOBILE_PATTERN, message = "手机号格式错误")
    String mobile;

    @Pattern(regexp = AccountConstants.PASSWORD_PATTERN, message = "密码格式错误")
    String password;

    @NotEmpty(message = "验证码不能为空")
    String inputVerifyCode;
}
