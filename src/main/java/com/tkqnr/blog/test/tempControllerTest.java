package com.tkqnr.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class tempControllerTest {

    //http://localhost:8000/blog/temp/home
    @GetMapping("/temp/home")
    public String tempHome(){
        System.out.println("tempHome");
        //파일리턴 기본경로 : src/main/resource/static + /home.html
        return "/home.html";
    }

    @GetMapping("/temp/img")
    public String tempImg(){
        return "/a.png";
    }
    @GetMapping("temp/jsp")
    public String tempJsp(){
        return"/test";
    }




}
