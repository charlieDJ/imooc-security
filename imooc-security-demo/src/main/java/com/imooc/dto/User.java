package com.imooc.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.validator.MyConstraint;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

public class User {


    public interface UserSimpleView {
    };

    public interface UserDetailView extends UserSimpleView {
    };
    @Past(message = "生日必须是过去的时间")
    private Date birthday;
    private String id;
    @MyConstraint(message = "这是一个测试")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @JsonView(UserDetailView.class)
    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }
    @JsonView(UserSimpleView.class)
    public void setId(String id) {
        this.id = id;
    }
    public Date getBirthday() {
        return birthday;
    }
    @JsonView(UserSimpleView.class)
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
