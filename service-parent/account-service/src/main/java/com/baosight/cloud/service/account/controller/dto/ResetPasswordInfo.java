package com.baosight.cloud.service.account.controller.dto;

import com.baosight.cloud.service.account.constants.AccountConstants;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by yang on 2018/3/22.
 */
@Data
public class ResetPasswordInfo  implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "旧密码不能为空")
    String oldPassword;

    @Pattern(regexp = AccountConstants.PASSWORD_PATTERN, message = "新密码格式错误")
    String newPassword;

}
