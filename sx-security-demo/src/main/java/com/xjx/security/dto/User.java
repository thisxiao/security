package com.xjx.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.xjx.security.validator.MyConstraint;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author XJX
 * @Date 2020/6/30 10:44
 * @Version 1.0
 */
@Data
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -5862077466914061886L;

    public interface UserSimpleView {}
    public interface UserDetailView extends UserSimpleView {}

    @JsonView(UserSimpleView.class)
    private String id;

    //@MyConstraint(message = "这是一个测试")
    @JsonView(UserSimpleView.class)
    private String username;

    @NotBlank(message = "密码不能为空")
    @JsonView(UserDetailView.class)
    private String password;

    @Past(message = "必须是过去的时间")
    @JsonView(UserSimpleView.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date birthday;
}
