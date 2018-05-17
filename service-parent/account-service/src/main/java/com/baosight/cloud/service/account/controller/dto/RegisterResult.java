package com.baosight.cloud.service.account.controller.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yang on 2018/3/20.
 */
@Data
public class RegisterResult implements Serializable {

    private static final long serialVersionUID = 1L;

    boolean registered;

    String msg;

    String userId;
}
