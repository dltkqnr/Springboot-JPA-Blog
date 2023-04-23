package com.tkqnr.blog.test;

import org.springframework.web.bind.annotation.*;

//사용자가 요청 -> 응답 (HTML 파일)
//@Controller

//사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

	private static final String TAG ="HttpControllerTest:";

	@GetMapping("/http/lombok")
	public String lombokTest(){
		//builder 패턴
		Member m = Member.builder().password("1234").email("tkqnr@naver.com").username("tkqnr").build();
		System.out.println(TAG+"getter: "+m.getId());
		m.setId(5000);
		System.out.println(TAG+"setter: "+m.getId());

		return "lombok test 완료";
	}

	//인터넷 브라우저 요청은 무조건 get요청 밖에 할수가 없다.
	//http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(Member m) { //MessageConverter(스프링부트)가 알아서 매핑

		return "Get요청 :" +m.getId() +", " + m.getUsername()+", " + m.getPassword() + ", " + m.getEmail();
	}

	//http://localhost:8080/http/post(insert)
	@PostMapping("/http/post") //text/plain , application/json
	public String postTest(@RequestBody Member m) { //MessageConverter(스프링부트)가 알아서 매핑

		return "Post요청 :" +m.getId() +", " + m.getUsername()+", " + m.getPassword() + ", " + m.getEmail();
	}

	//http://localhost:8080/http/put(update)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {

		return "Put요청 :" +m.getId() +", " + m.getUsername()+", " + m.getPassword() + ", " + m.getEmail();
	}

	//http://localhost:8080/http/delete(delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}
}
