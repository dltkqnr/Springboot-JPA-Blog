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
