package com.baosight.cloud.service.account.persist.entity;

import lombok.Data;

/**
 * Created by yang on 2017/6/5.
 */
@Data
public class Account {

    String userId;

    String userName;

    String encryptedPassword;

    String mobile;

    String email;

    String createDate;

    String status;

}
