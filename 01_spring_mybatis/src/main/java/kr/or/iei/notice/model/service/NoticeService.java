package kr.or.iei.notice.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.iei.notice.model.dao.NoticeDao;
import kr.or.iei.notice.model.vo.Notice;
import kr.or.iei.notice.model.vo.NoticeFile;
import kr.or.iei.notice.model.vo.NoticePageData;

@Service("noticeService")
public class NoticeService {
	
	@Autowired
	@Qualifier("noticeDao")
	private NoticeDao dao;

	public NoticePageData selectNoticeList(int reqPage) {
		
		//한 페이지에서 보여줄 게시글의 갯수
		int viewNoticeCnt = 10;
		
		//게시글 시작번호, 게시글 끝 번호
		//페이지당 10개씩 보여주니까, 1페이지 -> 1~10번 게시글, 2페이지 -> 11~20번 게시글
		
		int end = reqPage * viewNoticeCnt; // 1페이지 * 10 게시글 = 10번 게시글
		int start = end - viewNoticeCnt + 1; //10번 게시글 - 10개 + 1 = 1번 게시글
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", start);
		map.put("end", end);
		
		//게시글 리스트
		ArrayList<Notice> list = (ArrayList<Notice>) dao.selectNoticeList(map);
		
		//전체 게시글 갯수
		int totCnt = dao.selectNoticeCount();
		
		//전체 페이지 갯수
		int totPage = 0;
		
		//마지막 게시글 페이지 게시물 갯수 10개 미만이어도 하나 더 생성
		if(totCnt % viewNoticeCnt > 0) {
			totPage = totCnt / viewNoticeCnt + 1;
		}else {
			totPage = totCnt / viewNoticeCnt;
		}
		
		//페이지 내비게이션 사이즈
		int pageNaviSize = 5;
		
		//페이지 네비게이션 시작번호 설정 (1,2,3,4,5 == 1 or 6,7,8,9,10 == 6)
		int pageNo = ((reqPage-1) / pageNaviSize) * pageNaviSize + 1;
		
		//페이지 네비게이션 HTML
		String pageNavi = "";
		
		//이전 버튼 - 네비게이션 시작 페이지가 1이 아닐때
		if(pageNo != 1) {
			pageNavi += "<a href='/notice/getList.kh?reqPage=" + (pageNo-1) + "'> 이전 </a>"; 
		}
		
		//페이지 네비게이션
		for(int i=0; i<pageNaviSize; i++) {
			if(pageNo == reqPage) {
				pageNavi +="<span>" + pageNo + "</span>";
			}else {
				pageNavi += "<a href='/notice/getList.kh?reqPage=" + pageNo +"'>" + pageNo + "</a>";
			}
			
			pageNo++;
		
			if(pageNo > totPage) {
				break;
			}
		}
		
		//다음 버튼 - 페이지NO값이 전체 페이지보다 작을때
		if(pageNo <= totPage) {
			pageNavi += "<a href='/notice/getList.kh?reqPage=" + pageNo + "'>다음</a>";		
		}
		
		NoticePageData pd = new NoticePageData(list, pageNavi);
		return pd;
	}
	
	
	@Transactional
	/*
	 - RuntimeException 또는 Error만 롤백 대상이 된다.
	 - Transactional 어노테이션이 붙은 메소드에서 동일 클래스 내부에 다른 메소드 호출 시, 관리가 안된다.
	 */
	public int insertNotice(Notice notice, ArrayList<NoticeFile> fileList) {
		/*
		tbl_notice : insert
		tbl_notice_file : insert
		
		tbl_notice_file.notice_no는 tbl_notice.notice_no로 존재해야 한다.
		
		SQL 수행 순서)
		
		(1) tbl_notice - insert
			- SQL : insert into tbl_notice (notice_no) values (seq_notice.nextval)
		(2) tbl_notice_file - insert
		
		*/
		
		//등록될 게시글의 번호를 먼저 조회(notice 정보 및 notice_file 정보 등록 시 모두 필요하므로)
		String noticeNo = dao.selectNoticeNo();
		
		//tbl_notice(참조되는)와 tbl_notice_file(참조하는)은 참조 관계이므로, tbl_notice-insert를 선작업
		notice.setNoticeNo(noticeNo);
		int result = dao.insertNotice(notice);
		 
		if(result > 0) {			
			for(int i = 0; i < fileList.size();i++) {
				NoticeFile file = fileList.get(i);
				file.setNoticeNo(noticeNo);
				
				result = dao.insertNoticeFile(file);
			}			
		}
		
		return result;
	}
	
	public Notice selectOneNotice (String noticeNo) {
		Notice notice  = dao.selectOneNotice(noticeNo);
		
		if(notice != null) {
			ArrayList<NoticeFile> fileList = (ArrayList<NoticeFile>) dao.selectNoticeFileList(noticeNo);
			notice.setFileList(fileList);
		}
		return notice;
	}
	
	public ArrayList<NoticeFile> deleteNotice(String noticeNo) {	
		
		/*
		1) 현재 게시글에 대한 파일 정보를 조회
		2) 게시글 정보 DB에서 삭제 (tbl_notice)
		3) 게시글에 대한 파일 정보 DB에서 삭제(tbl_notice_file)
			--(2)번 수행 시, on delete cascade 설정에 의해 자동 삭제 처리
		4) 서버에 업로드된 파일 정보 삭제
		*/
		
		//1) 현재 게시글에 대한 파일 정보를 조회(서버에 업로드된 파일 정보를 조회)
		ArrayList<NoticeFile> list = (ArrayList<NoticeFile>) dao.selectNoticeFileList(noticeNo);
		
		//2) 게시글 정보 DB 삭제
		int result = dao.deleteNotice(noticeNo);
		
		if(result>0) {
			return list;
		}else {
			return null;
		}
	}

	@Transactional
	public ArrayList<NoticeFile> updateNotice(Notice notice, ArrayList<NoticeFile> fileList) {
		/*
		1) 게시글 정보 수정(tbl_notice)
		2) 서버에서 기존 파일 정보를 삭제하기 위한 조회
		3) 기존 파일 정보 삭제
		4) 업로드한 파일 정보 등록(tbl_notice_file)
		
		*/
		
		//1) 게시글 정보 수정
		int result = dao.updateNotice(notice);
		
		ArrayList<NoticeFile> delFileList = null;
		if(result > 0){
			//2) 서버에서 기존파일 정보를 삭제를 위한 파일 리스트 조회
			delFileList = (ArrayList<NoticeFile>) dao.selectNoticeFileList(notice.getNoticeNo());
	
			//3) 기존 파일 정보 DB에서 삭제 처리
			result = dao.deleteNoticeFile(notice.getNoticeNo());
			
			if(result > 0 && fileList.size() > 0) {
				//4) 업로드한 파일 정보 DB에 등록 처리
				for(NoticeFile insFile : fileList) {
					dao.insertNoticeFile(insFile);
				}
			}
		}
		
		return delFileList;
	}

}
