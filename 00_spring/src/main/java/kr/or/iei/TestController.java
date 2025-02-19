package kr.or.iei;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.or.iei.member.model.vo.Member;

/*
 Component : Spring 컨테이너에서 관리되는 특정 기능을 담당하는 객체
  - Controller : Spring MVC에서 웹 요청을 처리하는 컨트롤러
 */


@Controller
public class TestController {
	
	public TestController() {
		System.out.println("TestController가 생성 되었습니다.");
	}
	
	//GetMapping : 클라이언트 Get 요청을 처리할 수 있는 어노테이션
	//메소드 하나가 서블릿 역할을 한다.
	@GetMapping("/getTest.kh")
	public void getTest() {
		System.out.println("getTest 메소드 실행");
	}
	
	
	//PostMapping : 클라이언트 Post 요청을 처리할 수 있는 어노테이션
	@PostMapping("/postTest.kh")
	public void postTest() {
		System.out.println("postTest 메소드 실행");
	}
	
	
	//기존 서블릿 방식의 요청 파라미터 처리
	@GetMapping("/servletParamTest.kh")
	public void servletParamTest(HttpServletRequest req) {
		String testId = req.getParameter("testId");
		String testPw = req.getParameter("testPw");
		
		System.out.println("testId : " + testId);
		System.out.println("testPw : " + testPw);
	}
	
	//스프링에서의 요청 파라미터 처리 - 1
	//input 태그 name 속성 값과, 컨트롤러 매개변수 명칭과 일치시킨다.
	@PostMapping(value="/springParamTest.kh")
	public void springParamTest(String testId, String testPw) {
		System.out.println("testId : " + testId);
		System.out.println("testPw : " + testPw);
	}
	
	//스프링에서의 요청 파라미터 처리 - 2
	//input 태그 name 속성 값과, 컨트롤러 매개변수 클래스의 변수명과 일치시킨다.
	@PostMapping("/springParamTest2.kh")
	public void springParamTest2(Member member) {
		System.out.println("memberId : " + member.getMemberId());
		System.out.println("memberPw : " + member.getMemberPw());
		System.out.println("memberName : " + member.getMemberName());
		System.out.println("memberPhone : " + member.getMemberPhone());
	}
	
	//기존 서블릿 방식의 데이터 응답
	@GetMapping("/servletResponseTest.kh")
	public void servletResponseTest(HttpServletRequest req, HttpServletResponse res) {
		req.setAttribute("resTest1", "servlet responseData 1");
		req.setAttribute("resTest2", "servlet responseData 2");
		
		RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/resTest/servletResponse.jsp");
		
		try {
			view.forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//스프링 방식의 데이터 응답 - 1
	@GetMapping("/springResponseTest1.kh")
	public String springResponseTest1(Model model) {  //Model : 데이터만을 등록하고 응답할 때 사용하는 클래스
		model.addAttribute("resTest1", "spring responseData 1");
	    model.addAttribute("resTest2", "spring responseData 2");
       /*

		 servlet-context.xml 일부코드
		 
		 prefix : /WEB-INF/views
		 suffix : .jsp
		 
		 		prefix					return 값			    + suffix
		 /WEB-INF/views/  +  resTest/springResponse 	+      	    .jsp
		 */
		return "resTest/springResponse";
	}
	
	//스프링 방식의 데이터 응답 - 2
	@GetMapping("/springResponseTest2.kh")
	public ModelAndView springResponseTest2() {
		
		//ModelAndView : 응답 데이터와, 페이지 경로를 같이 등록할 때 사용되는 클래스
		ModelAndView mdv = new ModelAndView("resTest/springResponse2");
		mdv.addObject("resTest1", "spring responseData 1");
		mdv.addObject("resTest2", "spring responseData 2");
		
		return mdv;
	}
	
	//스프링 요청 및 응답 처리
	@PostMapping("/springReqAndResTest.kh")
	public String springReqAndResTest(Member member, Model model) {
		System.out.println("memberId : " + member.getMemberId());
		System.out.println("memberPw : " + member.getMemberPw());
		
		//응답 페이지에서 멤버 정보를 출력하기 위해, Model 객체에 "member"라는 키 값으로 등록
		model.addAttribute("member", member);
		
		/*
		/WEB-INF/views + resTest/springReqAndRes + .jsp
		*/
		
		return "resTest/springReqAndRes";
	}
}
