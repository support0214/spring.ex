package com.kh.spring.demo.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.demo.model.vo.Dev;

/*
@Controller 클래스의 handler 메소드가 가질수 있는 매개변수 타입
HttpServletRequest
HttpServletResponse
HttpSession

java.util.Locale: 요청에 대한 Locale
InputStream/Reader: 요청에 대한 입력스트림
OutputStream/Writer : 응답에 대한 출력스트림. ServletOutputStream, PrintWriter

사용자입력값처리
Command객체 : http요청 파라미터를 커맨드객체에 저장한 VO객체
CommandMap : HandlerMethodArgumentResolver에 의해 처리된 사용자입력값을 가진 Map 객체 
@Valid : 커맨드 객체 유효성 검사객체
Error, BindingResult: Command객체에 저장결과(Command객체 바로 다음위치시킬것.)
@PathVariable : 요청url 중 일부를 매개변수로 취할 수 있다.
@RequestParam: 사용자입력값을 자바변수에 대입처리(필수여부 설정)
@RequestHeader : 헤더값
@CookieValue: 쿠키값
@RequestBody: http message body에 작성된 json을 vo객체로 변환처리

뷰에 전달할 모델 데이터 설정
ModelAndView
ModelMap
Model

@ModelAttribute : model 속성에 대한 getter
@SessionAttribute : session속성에 대한 getter (required여부 선택가능)
@SessionAttributes : session에서 관리될 속성명을 class-level에 작성
SessionStatus: @SessionAttributes로 등록된 속성에 대하여 사용완료 (complete)처리. 세션을 폐기하지 않고 재사용한다.

기타
MultipartFile: 업로드파일 처리 인터페이스. CommonsMultipartFile
RedirectAttributes : DML처리후 요청주소 변경을 위한 redirect 속성처리 지원
*/

//   사용자의 요청을 처리, 정해진 뷰에 객체를 넘겨주는 역할
// @RequestMapping("/demo") // 하위 맵핑값에 모두 /demo가 들어간 것 !!
@Controller
public class DemoController {
      // 필요한 의존객체의 타입에 맞는 빈을 찾아 주입한다
      @Autowired
      private DemoService demoService;
   
      // @RequestMapping(value="/demo/devForm.do", method=RequestMethod.GET)
      @RequestMapping("/demo/devForm.do")
      public String devForm() {
         return "/demo/devForm";
      }
      
      @RequestMapping("/demo/dev1.do")
      public String dev1(HttpServletRequest request, HttpServletResponse response) {
         // 값을 가져오기
         String name = request.getParameter("name");
         int career = Integer.parseInt(request.getParameter("career"));
         String email = request.getParameter("email");
         String gender = request.getParameter("gender");
         String[] lang = request.getParameterValues("lang");
         
         // bean 생성
         Dev dev = new Dev(0, name, career, email, gender, lang, LocalDateTime.now()); // 객체에 담기
         System.out.println("dev =" + dev); // 오류나는지 확인
         
         request.setAttribute("dev", dev);
         return "demo/devResult";
      }
      
      // handler 메소드: @Controller하위의 사용자 요청 처리 메소드
      // - 속성 
      // 	- value : path속성에 대한 별칭, value속성명 유일하게 생략가능
      //	- method 생략시 모든 전송방식에 대해 처리
      //			특정 method등록 시 해당 전송방식만 처리
      @RequestMapping("/demo/dev2.do")
      public String dev2(@RequestParam String name,
    		  @RequestParam int career,
    		  @RequestParam String email,
    		  @RequestParam String gender,
    		  @RequestParam String[] lang,
    		  Model model) {
    	  
    	  // 사용자 입력값 처리
    	  Dev dev = new Dev(0, name, career, email, gender, lang, LocalDateTime.now());
    	  System.out.println("dev =" + dev);
    	  
    	  // jsp 전달
    	  model.addAttribute("dev", dev);
    	  return "demo/devResult";
      }
      
      @RequestMapping("/demo/dev3.do")
      public String dev3(Dev dev) {
    	  dev.setCreateDate(LocalDateTime.now());
    	  return "demo/devResult";
      }
}





