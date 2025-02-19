package kr.or.iei.member.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.vo.Member;

@Service//이 컴포넌트를 작성하면 서버 구동 시 자동으로 객체를 생성하여 IoC 컨테이너에 등록함.
public class MemberService {
	
		@Autowired //자동으로 생성되어 IoC 컨테이너에 등록된 객체를 주입받기 위함
		@Qualifier("memberDao")
		private MemberDao memberDao; //"memberDao" 식별자를 가진 객체를 주입받기 위함
		
		@Autowired 
		@Qualifier("jdbcTemplate")
		private JdbcTemplate jdbcTemplate;
		
		public MemberService() { 
			System.out.println("MemberService 생성 !");
		}
					
		public Member memberLogin(Member member) {
			return memberDao.memberLogin(member);
		}

		public int join(Member m) {
			return memberDao.memberJoin(m);
		}

		public int delete(String memberId) {
			// TODO Auto-generated method stub
			return memberDao.delete(memberId);
		}

		public ArrayList<Member> allMemberList() {
			return (ArrayList<Member>) memberDao.allMemberList();
			
		}

		public int idDuplChk(String memberId) {
			return memberDao.idDuplChk(memberId);
		}
		
}
