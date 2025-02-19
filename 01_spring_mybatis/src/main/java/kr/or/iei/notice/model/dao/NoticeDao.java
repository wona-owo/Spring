package kr.or.iei.notice.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import kr.or.iei.notice.model.vo.Notice;
import kr.or.iei.notice.model.vo.NoticeFile;

@Repository("noticeDao")
public class NoticeDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;

	
	public List<Notice> selectNoticeList(HashMap<String, Integer> map) {
		return sqlSession.selectList("notice.selectNoticeList",map);
	}


	public int selectNoticeCount() {
		return sqlSession.selectOne("notice.selectNoticeCount");
	}


	public int insertNotice(Notice notice) {
		return sqlSession.insert("notice.insertNotice", notice);
	}
	
	public int insertNoticeFile(NoticeFile file) {
		return sqlSession.insert("notice.insertNoticeFile", file);
	}


	public String selectNoticeNo() {
		return sqlSession.selectOne("notice.selectNoticeNo");
	}


	public Notice selectOneNotice(String noticeNo) {
		return sqlSession.selectOne("notice.selectOneNotice", noticeNo);
	}


	public List<NoticeFile> selectNoticeFileList(String noticeNo) {
		return sqlSession.selectList("notice.selectNoticeFileList", noticeNo);
	}


	public int deleteNotice(String noticeNo) {
		return sqlSession.delete("notice.deleteNotice",noticeNo);
	}


	public int updateNotice(Notice notice) {
		return sqlSession.update("notice.updateNotice",notice);		
	}


	public int deleteNoticeFile(String noticeNo) {
		return sqlSession.delete("notice.deleteNoticeFile",noticeNo);
	}
}
