package com.baosight.cloud.service.account.controller.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by yang on 2018/3/21.
 */
@Data
public class TryLoginResult implements Serializable {

    private static final long serialVersionUID = 1L;

    boolean status;

    String msg;

    String userId;
}
