package com.imooc.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.Data;
import com.imooc.dto.User;
import com.imooc.dto.UserJson;
import com.imooc.dto.UserQueryCondition;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }
        System.out.println(user.getId());
        System.out.println(user.getPassword());
        System.out.println(user.getUsername());
        System.out.println(user.getBirthday());
        user.setId("1");
        return user;
    }


    @GetMapping()
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "查询用户")
    public List<User> query(UserQueryCondition condition, @PageableDefault(page=2,size=20,sort="age,asc") Pageable pageable){
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

    @GetMapping("/{id}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id){//把url路径中的变量传递到参数中
//        throw new UserNotExistException("23");
        System.out.println("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PutMapping("/{id:\\d+}")
    public User update(@Valid @RequestBody User user, BindingResult errors){
        if(errors.hasErrors()){
            errors.getAllErrors().stream().forEach(error->{
                FieldError fieldError = (FieldError) error;
                String message = fieldError.getField()+"   "+error.getDefaultMessage();
                System.out.println(message);
            });
        }
        return user;
    }

    @PostMapping("json")
    public String testJson(@RequestBody UserJson userJson) throws Exception {
        Data data = userJson.getData();
        System.out.println(data.getName());
        System.out.println("111111");
        return "";
    }

    @GetMapping("length")
    public String testJson(@Valid String username) throws Exception {
        System.out.println(username);
        return "";
    }

}
