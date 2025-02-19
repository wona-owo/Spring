package kr.or.iei.member.model.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.iei.member.model.vo.Member;

@Repository //이 컴포넌트를 작성하면 서버 구동 시 자동으로 객체를 생성하여 IoC 컨테이너에 등록함.
public class MemberDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao() {
		System.out.println("MemberDao 생성!");
	}

	public Member memberLogin(Member member) {
		//1. 쿼리 작성
		String query = "select * from tbl_member where member_id =? and member_pw =?";
		
		//2. 파라미터 작성(오브젝트 배열로 작성)
		Object [] paramArr= {member.getMemberId(), member.getMemberPw()};
		
		//3. SQL 수행
		//MemberRowMapper : 데이터베이스 조회 결과를 Java 객체로 변환하는 역할을 담당
		Member loginMember = jdbcTemplate.queryForObject(query, paramArr, new MemberRowMapper());
		
		//4. 결과 반환
		return loginMember;
	}

	public int memberJoin(Member m) {
		//1. 쿼리 작성
		String query = "insert into tbl_member values (?, ?, ?, ?, ?, ?, sysdate)";
		
		//2. 파라미터 작성
		Object [] paramArr = {m.getMemberId(), m.getMemberPw(), m.getMemberName(), m.getMemberPhone(), m.getMemberAge(), m.getMemberGender()};
		
		//3. SQL 수행
		int cnt = jdbcTemplate.update(query, paramArr);
		return cnt;
	}

	public int delete(String memberId) {
		//1. 쿼리 작성
		String query = "delete from tbl_member where member_id = ?";
		
		//2. 파라미터
		Object [] paramArr = {memberId};
		
		//3. SQL 수정
		int result = jdbcTemplate.update(query, paramArr);
		
		//4. 결과 반환
		return result;
		
	}

	public List<Member> allMemberList() {
		//1. 쿼리작성
		String query = "select * from tbl_member";
		
		//2. 파라미터 작성 - 쿼리 수행 시 전달값 없으므로 선언하지 않음.

		//3. SQL 수행
		List<Member> list = jdbcTemplate.query(query, new MemberRowMapper());
		
		//4. 결과 반환
		return list;
	}

	public int idDuplChk(String memberId) {
		//1. 쿼리 작성
		String query = "select count(*) from tbl_member where member_id =?";
		
		//2.파라미터 작성
		Object [] paramArr = {memberId};
		
		//3. SQL 수행
		int cnt = jdbcTemplate.queryForObject(query, paramArr, Integer.class);
		
		//4. 결과 반환
		return cnt;
	}
	
	
}
