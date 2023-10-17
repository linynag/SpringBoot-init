package com.linynag.springbootinit.model.dto.user;

import java.io.Serializable;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

/**
 * 用户登录请求
 *
 */
@Data
@Validated
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;
}
