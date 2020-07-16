package com.xjx.security.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.xjx.security.dto.User;
import com.xjx.security.dto.UserQueryCondition;
import com.xjx.security.exception.UserNotExistException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author XJX
 * @Date 2020/6/30 10:42
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "用管理接口",tags = "管理用户增删改查")
public class UserController {

    @Resource
    private ProviderSignInUtils providerSignInUtils;

    @PostMapping("/register")
    public void register(User user, HttpServletRequest request){

        //不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
        log.info("注册用户信息：{}",user);
        String userId = user.getUsername();
        providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
    }

    @GetMapping("/me")
    @ApiOperation("获得当前用户信息")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }


    @PostMapping
    //@JsonView(User.UserSimpleView.class)
    public User create(@Valid @RequestBody User user){
//        if(br.hasErrors()){
//            br.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
//        }
        System.out.println(user);

        user.setId("1");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user ,BindingResult br ,@PathVariable("id") String id){
        if(br.hasErrors()){
            br.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user);

        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable("id") String id){
        System.out.println(id);
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    @ApiOperation("用户分页查询")
    public List<User> query(UserQueryCondition condition, @PageableDefault(page = 2,size = 15,sort = "username,asc") Pageable pageable){
        System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));

        System.out.println(pageable.getPageSize());
        System.out.println(pageable.getPageNumber());
        System.out.println(pageable.getSort());
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    @ApiOperation("用户查询")
    public User getInfo(@ApiParam("用户id") @PathVariable("id") String id){
//        throw new RuntimeException("user not exist");
        log.info("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @GetMapping("/test")
    @JsonView(User.UserDetailView.class)
    @ApiOperation("用户查询")
    public User getInfoTest(){
//        throw new RuntimeException("user not exist");
        log.info("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

}
