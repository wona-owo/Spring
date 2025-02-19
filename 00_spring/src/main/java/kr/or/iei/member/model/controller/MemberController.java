package kr.or.iei.member.model.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

@Controller  //이 컴포넌트를 작성하면 서버 구동 시 자동으로 객체를 생성하여 IoC 컨테이너에 등록함.
@RequestMapping("/member/")//현재 컨트롤로 내부에 작성된 메소드로 요청 시, 모드 시작 URL이 동일하므로, 공통 URL 설정 (아래 매핑란에는 뒷부분만 작성하면 됨)
public class MemberController {
	
	@Autowired //IoC 컨테이너에 등록된: 객체(Bean) 중에, 타입이 일치하는 객체(Bean)를 자동으로 주입받음.
	@Qualifier("memberService") //IoC 컨테이너애ㅔ 등록된 객체(Bean) 중에, 명칭이 동일한 객체를 주입 받기 위함
	private MemberService memberService;
	
	public MemberController( ) {
		System.out.println("MemberContoller 생성 !");
	}
	
	@PostMapping("login.kh")
	public String memberLogin(Member member, HttpSession session) {
		//1. 인코딩 - web.xml
		//2. 값추출 - 매개변수로 전달
		//3. 비즈니스 로직
		
		//@Controller 컴포넌트를 통해, 자동으로 생성되어 IoC 컨테이너 등록된 객체를, @Autowired가 작성된 변수에 주입ㅂ다았으므로, 객체의 주소값이 출력된다.
		//System.out.println(memberService); //주소값 출력
		Member loginMember = memberService.memberLogin(member);
		
		//4. 결과처리
		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			return "member/loginSuccess";
		}else {
			return "member/loginFail";
		}
	}
	
	@GetMapping("joinFrm.kh")
	public String joinFrm() {
		//prefix : /WEB-INF/views/
		//suffix : .jsp
		return "member/join";
	}
	
	@PostMapping("join.kh")
	public String join(Member m) {
		/*
		System.out.println(m.getMemberId());
		System.out.println(m.getMemberPw());
		System.out.println(m.getMemberName());
		System.out.println(m.getMemberPhone());
		System.out.println(m.getMemberAge());
		System.out.println(m.getMemberGender());
		*/
		//1. 인코딩 필터
		//2. 값 추출 - 매개변수에 작성된 member에 서명
		//3. 로직
		int result = memberService.join(m);
		//4. 결과 처리
		
		//result > 0 == joinSuccess.jsp
		//else       == joinFail.jsp
		
		if(result > 0) {
			return "member/joinSuccess";
		}else {
			return "member/joinFail";
		}
	}
	
	@GetMapping("logout.kh")
	public String logout(HttpSession session) {
		session.invalidate();
		
		//서블릿 프로젝트에서 response.sendRedirect와 같은 역할
		return "redirect:/";
	}
	
	@GetMapping("mypage.kh")
	public String mypage() {
		
		return "member/mypage";
	}
	
	@GetMapping("delete.kh")
	public String delete(String memberId) {
		
		int result = memberService.delete(memberId);
		
		if(result > 0) {
			//메인페이지
			return "redirect:/logout.kh"; //위쪽 메소드로 전달해서 새션 파기
		}else {
			//deleteFail.jsp로 이동
		}
		return "";
	}
	
	@GetMapping("allMemberList.kh") //Model 객체를 데이터를 등록하고 응답할때 사용. list를 등록해준다.(이따 꺼내쓰게)
	public String allMemberList(Model model) {
		ArrayList<Member> list = memberService.allMemberList();
		model.addAttribute("memberList",list);
		
		return "member/allMember";
	}
	
	@GetMapping("idDuplChk.kh")
	@ResponseBody
	public String idDuplChk(String memberId) {
		
		int cnt = memberService.idDuplChk(memberId);
		
		return String.valueOf(cnt);
	}
}
