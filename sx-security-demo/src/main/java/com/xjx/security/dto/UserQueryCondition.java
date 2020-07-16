package com.xjx.security.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @Author XJX
 * @Date 2020/6/30 11:01
 * @Version 1.0
 */
@Data
public class UserQueryCondition {

    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("用户年龄起始值")
    private Integer age;
    @ApiModelProperty("用户年龄终止值")
    private Integer ageTo;
    @ApiModelProperty("用户性别")
    private String gender;
}
