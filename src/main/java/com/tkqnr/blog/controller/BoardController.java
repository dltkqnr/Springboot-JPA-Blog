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
    public String index(){
        // /WEB-INF/views/index.jsp
        return "index";
    }

    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm(){
        return "board/saveForm";
    }

}
