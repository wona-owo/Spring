package kr.or.iei.member.controller;

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

import com.google.gson.Gson;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	@Qualifier("service")
	private MemberService memberService;	
	
	public MemberController() {
		System.out.println("MemberController 생성 !");
	}
	
	@PostMapping("login.kh")
	public String memberLogin(Member member, HttpSession session) {
		//1. 인코딩 - 필터
		//2. 값 추출 - 매개변수에 작성된 member에 할당되어 있다.
		//3. 로직 - 로그인
		
		//MemberService 클래스 상단에 Service 어노테이션(컴포넌트)을 작성하여, 
		//서버 구동 시 자동으로 생성된 객체(Bean)를 Autowired 어노테이션을 통해 주입 받았으므로, new 연산자(객체 생성)없이 사용이 가능하다.
		
		Member loginMember = memberService.memberLogin(member);
		
		if(loginMember != null) {
			session.setAttribute("loginMember", loginMember);
			return "redirect:/";
		}else {
			//servlet-context에 prefix와 suffix로 자동으로 앞뒤에 붙는다.
			return "member/loginFail";
		}
	}
	
	@GetMapping("joinFrm.kh")
	public String joinFrm() {
		return "member/join";
	}
	
	@PostMapping("join.kh")
	public String join(Member member) {
		
		int result = memberService.join(member);
		if(result > 0) {
			return "redirect:/";
		}else {
			return "member/joinFail";
		}
		
		
	}
	
	@GetMapping("idDuplChk.kh")
	@ResponseBody
	public String idDuplChk(String memberId) {
		
		int cnt = memberService.idDuplChk(memberId);		
		return String.valueOf(cnt);
	}
	
	@GetMapping("allMember.kh")
	public String allMember() {
		return "member/allMemberList";
	}
	
	
	@GetMapping(value="allMemberList.kh", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String allMemberList() {		
		ArrayList<Member> list = memberService.allMemberList();
		
		return new Gson().toJson(list);
		
	}
	
	@GetMapping("delete.kh")
	public String delete(HttpSession session) {
		
		Member loginMember = (Member) session.getAttribute("loginMember");
		
	    int result = memberService.delete(loginMember.getMemberId());
		
		if(result > 0) {
			session.invalidate();
			return "redirect:/";
		}else {
			return "member/deleteFail";
		}
				
	}
	
	@GetMapping("logout.kh")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	@GetMapping("mypage.kh")
	public String myPage(HttpSession session) {
		Member loginMember = (Member) session.getAttribute("loginMember");
		
			
		return "member/myPage";
	}
	
	@PostMapping("update.kh")
	public String updateMember(Member member, HttpSession session) {
		int result = memberService.updateMember(member);
		
		if(result > 0) {
			Member loginMember = (Member)session.getAttribute("loginMember");
			loginMember.setMemberPw(member.getMemberPw());
			loginMember.setMemberName(member.getMemberName());
			loginMember.setMemberPhone(member.getMemberPhone());
			loginMember.setMemberGender(member.getMemberGender());
			
			return "redirect:/";
		}else {
			return "member/updateFail";
		}
	}
}













