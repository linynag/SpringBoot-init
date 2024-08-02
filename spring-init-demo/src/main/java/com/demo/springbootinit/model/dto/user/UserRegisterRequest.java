package com.demo.springbootinit.model.dto.user;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 用户注册请求体
 */
@Data
@Validated
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    @NotNull(message = "账号不能为空")
    @Size(min = 4, max = 8, message = "账号必须4-8位")
    private String userAccount;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, message = "密码必须大于8位")
    private String userPassword;

    @NotBlank(message = "密码不能为空")
    @Size(min = 8, message = "密码必须大于8位")
    private String checkPassword;
}
