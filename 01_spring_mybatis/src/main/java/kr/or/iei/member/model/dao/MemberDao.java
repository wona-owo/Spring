package kr.or.iei.member.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import kr.or.iei.member.model.vo.Member;

@Repository("dao")
public class MemberDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
		
	public MemberDao() {
		System.out.println("MemberDao 생성!");
	}


	public Member memberLogin(Member member) {
		return sqlSession.selectOne("member.selectOneMember", member);
	
	}


	public int join(Member member) {
		return sqlSession.insert("member.insertMember", member);
	}


	public int idDuplChk(String memberId) {
		return sqlSession.selectOne("member.idDuplChk", memberId);
	}


	public List<Member> allMemberList() {
		return sqlSession.selectList("member.allMember");
	}


	public int delete(String memberId) {
		return sqlSession.delete("member.delete", memberId);
	}


	public int updateMember(Member member) {
		return sqlSession.update("member.update", member);
	}
}
