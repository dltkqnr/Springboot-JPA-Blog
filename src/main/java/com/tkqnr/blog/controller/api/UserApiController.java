package com.tkqnr.blog.controller.api;

import com.tkqnr.blog.dto.ResponseDto;
import com.tkqnr.blog.model.RoleType;
import com.tkqnr.blog.model.User;
import com.tkqnr.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class UserApiController {

    @Autowired
    private UserService userService;


    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user){ //username,password,email
        System.out.println("UserApiController : save 호출됨");

        user.setRole(RoleType.USER);
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //자바 Object를 Json으로 변환해서 리턴 (Jackson)
    }

    /*
    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user,HttpSession session){
        System.out.println("UserApiController : login 호출됨");
        User principal = userService.로그인(user); //principal(접근주체)

        if(principal != null){
            session.setAttribute("principal",principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
     */

}
