package com.tkqnr.blog.controller;

import com.tkqnr.blog.config.auth.PrincipalDetail;
import com.tkqnr.blog.config.auth.PrincipalDetailService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    private PrincipalDetailService principal;

    //@AuthenticationPrincipal PrincipalDetail principal
    @GetMapping({"","/"})
    public String index(@AuthenticationPrincipal PrincipalDetail principal){
        // /WEB-INF/views/index.jsp
        System.out.println("로그인 사용자 아이디:"+principal.getUsername());
        return "index";
    }
}
