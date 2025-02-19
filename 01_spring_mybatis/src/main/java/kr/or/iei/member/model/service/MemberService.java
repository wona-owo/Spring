package kr.or.iei.member.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;

import kr.or.iei.member.model.dao.MemberDao;
import kr.or.iei.member.model.vo.Member;

@Service("service")
public class MemberService {
		
	    @Autowired
	    @Qualifier("dao")
		private MemberDao memberDao;
	    
	    public MemberService() {
	    	System.out.println("MemberService 생성!");
	    }

		public Member memberLogin(Member member) {
			return memberDao.memberLogin(member);
		}

		public int join(Member member) {
			return memberDao.join(member);
		}

		public int idDuplChk(String memberId) {
			return memberDao.idDuplChk(memberId);
		}

		public ArrayList<Member> allMemberList() {
			return (ArrayList<Member>) memberDao.allMemberList();
		}

		public int delete(String memberId) {
			return memberDao.delete(memberId);
		}


		public int updateMember(Member member) {
			return memberDao.updateMember(member);
		}
	
}
