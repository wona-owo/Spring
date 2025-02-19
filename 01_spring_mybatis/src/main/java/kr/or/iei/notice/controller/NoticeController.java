package kr.or.iei.notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kr.or.iei.notice.model.service.NoticeService;
import kr.or.iei.notice.model.vo.Notice;
import kr.or.iei.notice.model.vo.NoticeFile;
import kr.or.iei.notice.model.vo.NoticePageData;

@Controller
@RequestMapping("/notice/")
public class NoticeController {

	@Autowired
	@Qualifier("noticeService")
	private NoticeService service;

	
	@GetMapping("getList.kh")
	public String getList(int reqPage, Model   model) {	
		NoticePageData pd = service.selectNoticeList(reqPage);
		
		//응답 페이지(list.jsp)에서 게시글 목록을 보여주기 위해, 응답 데이터를 등록할 수 있는 Model 객체 매개변수에 추가.
		model.addAttribute("list", pd.getList());
		model.addAttribute("pageNavi", pd.getPageNavi());
		
		return "notice/list";
	}
	
	@GetMapping("writeFrm.kh")
	public String writeFrm() {
		return "notice/write";
	}
	
	@PostMapping("write.kh")
	public String write(HttpServletRequest request , MultipartFile [] files, Notice notice) {
		//서비스에 파일 정보를 전달하기 위한 ArrayList
		ArrayList<NoticeFile> fileList = new ArrayList<NoticeFile>();
		
		
		for(int i=0; i<files.length; i++) {
			
			MultipartFile file = files[i];
			
			if(!file.isEmpty()) {
				String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/notice/");			
		
				String originalFileName = file.getOriginalFilename(); //업로드한 파일명 (test1.txt)
				String fileName = originalFileName.substring(0,originalFileName.lastIndexOf(".")); //test1
				String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); //.txt
				
				String toDay = new SimpleDateFormat("yyyyMMdd").format(new Date()); //오늘 날짜
				int ranNum = new Random().nextInt(10000)+1; //1~10000 사이 랜덤 숫자
				String filePath = fileName + "_" + toDay + "_" + ranNum + extension; //test1 + _ + 20241205 + _랜덤숫자 + .txt => test1_20241205_4525.txt
				
				savePath += filePath;
				
				//파일 업로드를 위한 보조 스트림
				BufferedOutputStream bos = null;
				
				try {
					byte [] bytes = file.getBytes();
					FileOutputStream fos = new FileOutputStream(new File(savePath));
					bos = new BufferedOutputStream(fos);
					bos.write(bytes);
					
					NoticeFile noticeFile = new NoticeFile();
					//tbl_notice_file (file_no, notice_no, file_name, file_path)
					noticeFile.setFileName(originalFileName);  //원본 파일명
					noticeFile.setFilePath(filePath);		   //업로드 파일명
					
					//리스트에 추가
					fileList.add(noticeFile);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						bos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		int result = service.insertNotice(notice, fileList);
		
	
		return "redirect:/notice/getList.kh?reqPage=1";
	}
	
	
	@GetMapping("detailView.kh")
	public String detailView(String noticeNo, Model model) {
		Notice notice = service.selectOneNotice(noticeNo);
		
		model.addAttribute("notice",notice);
		
		return "notice/detailView";
	}
	
	@GetMapping(value="fileDown.kh", produces="application/octet-stream;")
	public void noticeFileDown(HttpServletResponse response, HttpServletRequest request, String fileName, String filePath) {
		//파일 위치 절대 경로
		String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/notice/");
		
		//파일 다운로드를 위한 보조 스트림 선언
		BufferedOutputStream bos = null;  //읽어들인 파일을 사용자에게 내보내기(다운로드) 위한 보조 스트림
		BufferedInputStream bis = null;  //서버에서 파일을 읽어들이기 위한 보조 스트림
		
		try {
			FileInputStream fis = new FileInputStream(savePath + filePath);
			
			//보조스트림과 주스트림 연결
			bis = new BufferedInputStream(fis);
			
			//파일을 내보내기 위한 스트림 샹성
			ServletOutputStream sos = response.getOutputStream();
			
			//보조스트림과 주스트림 연결
			bos = new BufferedOutputStream(sos);
			
			//다운로드 파일명 설정
			String resFileName = new String(fileName.getBytes("Utf-8"),"ISO-8859-1");
			
			//브라우저에게 다운로드 지시 및 다운로드 파일명 지정
			response.setHeader("Content-Disposition", "attachment; fileName=" + resFileName);
			
			while(true) {
				int read = bis.read(); //바이트 단위로 파일 데이터 read
				if(read == -1) {//읽을 데이터 존재하지 않을 때 반복문 종료
					break;

				}else {
					bos.write(read);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				bos.close();
				bis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	@GetMapping("/delete.kh")
	public String deleteNotice(HttpServletRequest request, String noticeNo) {
		
		ArrayList<NoticeFile> fileList= (ArrayList<NoticeFile>) service.deleteNotice(noticeNo);
		
		if(fileList != null && fileList.size() >0 ) {
			//삭제 성공 시 목록으로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/notice");
			
			for(NoticeFile file : fileList) {
				File f= new File(savePath + file.getFilePath());
				if(f.exists()) {
					f.delete();
				}
			}
			
			return "redirect:/notice/getList.kh?reqPage=1";
		}else {
			//삭제 실패 시 메세지?
			return  "redirect:/notice/detail.kh?noticeNo=" + noticeNo + "&error=true";
			}
				
	}
	
	@GetMapping("updateFrm.kh")
	public String updateFrm(String noticeNo, Model model) {
		
		Notice notice = service.selectOneNotice(noticeNo);
		model.addAttribute("notice",notice);
		
		
		return"notice/update";
	}
	
	@PostMapping("update.kh")
	public String update(HttpServletRequest request, MultipartFile [] files, Notice notice) {

		ArrayList<NoticeFile> fileList = new ArrayList<NoticeFile>();
		
		
		for(int i=0; i<files.length; i++) {
			
			MultipartFile file = files[i];
			
			if(!file.isEmpty()) {
				String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/notice/");			
		
				String originalFileName = file.getOriginalFilename(); 
				String fileName = originalFileName.substring(0,originalFileName.lastIndexOf(".")); 
				String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); 
				
				String toDay = new SimpleDateFormat("yyyyMMdd").format(new Date()); 
				int ranNum = new Random().nextInt(10000)+1; 
				String filePath = fileName + "_" + toDay + "_" + ranNum + extension; 
				savePath += filePath;
				
			
				BufferedOutputStream bos = null;
				
				try {
					byte [] bytes = file.getBytes();
					FileOutputStream fos = new FileOutputStream(new File(savePath));
					bos = new BufferedOutputStream(fos);
					bos.write(bytes);
					
					NoticeFile noticeFile = new NoticeFile();
					/*
					게시글 신규 등록
					- tbl_notice와 tbl_notice_file은 참조관계이므로,
					  서비스에서 notice_no를 선 조회
					  
					  게시글 수정
					  - 수정이니까, 클라이언트 전달 파라미터 게시글에 대한 번호가 포함되어 있다.
					    전달된 notice_no를 NoticeFile 객체에 Set
					*/
					noticeFile.setNoticeNo(notice.getNoticeNo());
				
					noticeFile.setFileName(originalFileName);  
					noticeFile.setFilePath(filePath);		   
					
					fileList.add(noticeFile);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					try {
						bos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	
	   //기존 파일 정보 == 삭제 대상 파일 리스트(서버에서 가져와야 함. 삭제할 애들 전달만 받아찌 도로 전달 줘야함.)
	   ArrayList<NoticeFile> delFileList = service.updateNotice(notice, fileList);
		
	   if(fileList != null && delFileList.size() >0 ) {
			//삭제 성공 시 목록으로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/upload/notice");
			
			for(NoticeFile file : delFileList) {
				File f= new File(savePath + file.getFilePath());
				if(f.exists()) {
					f.delete();
				}
			}
	   }
	   return "notice/getList.kh?reqPage=1";
	}
}
