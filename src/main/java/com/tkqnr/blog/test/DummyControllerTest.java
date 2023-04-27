package com.tkqnr.blog.test;

import com.tkqnr.blog.model.RoleType;
import com.tkqnr.blog.model.User;
import com.tkqnr.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Supplier;

//html 파일이 아니라 data를 리턴해주는 controller = @RestController
@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입 DI
    private UserRepository userRepository;

    //전체 select
    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    //paging
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2,sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    //{id} 주소로 파라미터를 전달 받을 수 있음.
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
        // user/4를 찾으면, 내가 DB에서 못찾아오게되면 user가 null 이 될거지?
        // 그럼 return 할 때 null이 리턴이되서 프로그램에 문제가 있지않겠니?
        // Optional로 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해.
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id: "+id);
            }
        });
        //람다식 표현
        // User user = userRepository.findById(id).orElseThrow(()->{
        // return new IllegalArgumentException("해당 유저는 없습니다.id: "+id);
        // });

        //MessageConverter가 Jackson 라이브러리를 호출하여 user 오브젝트를 json으로 변환 후 응답
        return user;
   }

    //http://loaclhost:8000/blog/dummy/join (요청)
    //http의 body에 username,password,email 데이터를 가지고 요청
    @PostMapping("/dummy/join")
    public String join(User user){ //key=value (약속된규칙)
        System.out.println("username : "+ user.getUsername());
        System.out.println("password : "+ user.getPassword());
        System.out.println("email : "+ user.getEmail());
        System.out.println("role : "+ user.getRole()); //
        System.out.println("createDate : "+ user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }
}
