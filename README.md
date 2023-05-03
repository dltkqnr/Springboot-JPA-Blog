# Springboot-JPA-Blog



* 컨트롤러 생성
  *	@Controller 사용자의 요청 -> 응답 (HTML 파일)
  * @RestController 사용자의 요청 -> 응답 (Data)

* postman을 이용하여 http요청 테스트 (Get,Post,Put,Delete)
  * @RequestParam - get  -> Object 자동으로 Mapping
  *	@RequestBody - post,put,delete

* lombok 테스트 (@Data, @NoArgsConstructor, @AllArgsConstructor -> @Builder 테스트)
 
* application.yml 설정 
  * jsp 테스트 (prefix: /webapp/WEB-INF/views/  suffix :.jsp 경로 설정 및 확인)

* DB 저장을 위한 @Entity 설정, 각 변수들의 @Column 설정 ( User, Board, Reply )

* 연관관계 설정 (@ManyToOne, @OneToMany, @OneToOne)

* Json data로 통신 
  * Get 요청 : 주소에 데이터를 담아보냄. 데이터 형태는 key = value
  * Post, Put, Delete 요청 : Body에 데이터 담아 보냄. 데이터 형태는 json
  * 송신 : html 데이터 -> json으로 변환하여 전송
  * 수신 : json 데이터 -> 자바 오브젝트로 변환
  * Controller 에서 외부로 부터 request 요청이 올 때 싣어나르는 Body 데이터를 json으로 만듦

* 스프링 컨트롤러의 파싱 전략
  1. 스프링 컨트롤러는 key=value 데이터를 자동으로 파싱하여 변수에 담아줌. 
  2. key=value 형태의 데이터를 오브젝트로 파싱해서 받아주는 역할도 함 // setter가 없으면 안됨.
  3. key=value 가 아닌 데이터의 파싱?
    * json 데이터와 일반 text 데이터를 받기 위해서는 @RequestBody 어노테이션 필요
    * MessageConverter 클래스를 구현한 Jackson 라이브러리가 발동하면서 json 데이터를 자바 오브젝트로 파싱

* GlobalExceptionHandler를 이용한 예외처리

* 메인화면 
  * 부트스트랩을 이용하여 프론트 페이지 구현
  * index, 로그인, 회원가입 페이지 구현.(header,footer)

* 회원가입 기능 구현 (.js)
  * ajax 를 사용하는 이유
   1. 요청에 대한 응답을 html이 아닌 Data(Json)을 받기위해
     * 고객이 사용하는 대표적인 클라이언트 앱과 웹의 서버를 하나로 쓰기위해
   2. 비동기 통신을 하기 위해서
     * 비동기통신 요청을 하고 요청이 pending 되는 동안 다른 일을 할 수 있음.
  * UserApiController , ResponseDto,layout(jsp), user.js 
