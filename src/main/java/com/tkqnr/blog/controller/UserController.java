package com.tkqnr.blog.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tkqnr.blog.model.KakaoProfile;
import com.tkqnr.blog.model.OAuthToken;
import com.tkqnr.blog.model.User;
import com.tkqnr.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
//그냥 주소가 / 이면 index.jsp 허용
//static 이하에 있는 /js/**,/css/**/,/image/**

@Controller
public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${cos.key}")
    private String cosKey;

    @GetMapping("/auth/joinForm")
    public String joinForm(){
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(){
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateFrom(){
        return "user/updateForm";
    }

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code){ //Data를 리턴해주는 컨트롤러 함수

        //POST방식으로 key=value 데이터를 요청(카카오쪽으로)
        //Retrofit2, OkHttp, RestTemplate

        RestTemplate rt = new RestTemplate();

        //HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type"," application/x-www-form-urlencoded;charset=utf-8");

        //HttpBody 오브젝트 생성
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("grant_type","authorization_code");
        params.add("client_id","b7cf9a0668acea8ae778ce2dba2e4cac");
        params.add("redirect_uri","http://localhost:8000/auth/kakao/callback");
        params.add("code", code);

        //HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest =
                new HttpEntity<>(params,headers);

        //Http 요청하기 - POST 방식 - response 변수로 응답받음.
        ResponseEntity<String> response = rt.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        //Gson,Json Simple,ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("카카오 엑세스 토큰: "+ oauthToken.getAccess_token());

        //profile 정보 요청 (사용자 정보 가져오기)
        RestTemplate rt2 = new RestTemplate();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer "+oauthToken.getAccess_token());
        headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");


        HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest =
                new HttpEntity<>(headers2);

        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;

        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(),KakaoProfile.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // User 오브젝트 : username, password, email
        System.out.println("카카오 아이디(번호): "+ kakaoProfile.getId());
        System.out.println("카카오 이메일: "+ kakaoProfile.getKakao_account().getEmail());


        System.out.println("블로그서버 유저네임: "+kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId());
        System.out.println("블로그서버 이메일: "+ kakaoProfile.getKakao_account().getEmail());
        // UUID garbagePassword = UUID.randomUUID();
        //UUID란 -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘 -> 카카오로그인시 계속 패스워드가 바뀌어서 확인못함

        System.out.println("블로그서버 패스워드: " + cosKey);


        User kakaoUser = User.builder()
                        .username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
                        .password(cosKey)
                        .email(kakaoProfile.getKakao_account().getEmail())
                        .oauth("kakao")
                                .build();


        // 가입자 혹은 비가입자 체크 해서 처리

        User originUser = userService.회원찾기(kakaoUser.getUsername());

        if(originUser.getUsername() == null){
            System.out.println("신규 회원이므로 자동 회원가입 진행");
            userService.회원가입(kakaoUser);
        }

        // 로그인 처리
        System.out.println("자동 로그인 진행");
        Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),cosKey));
        SecurityContextHolder.getContext().setAuthentication(authentication);




        return "redirect:/";
    }
}
