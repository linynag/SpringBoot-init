package com.linynag.springbootinit.model.dto.user;

import java.io.Serializable;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录请求
 *
 */
@Data
@Validated
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;
    @NotBlank(message = "账号不能为空")
    private String userAccount;
    @NotBlank(message = "密码不能为空")
    private String userPassword;
}
