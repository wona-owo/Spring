/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.96
 * Generated at: 2024-12-18 06:35:50 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class join_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.LinkedHashSet<>(4);
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>회원가입</title>\r\n");
      out.write("<link rel =\"stylesheet\"  href=\"/resources/default.css\">\r\n");
      out.write("<script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<header>\r\n");
      out.write("	<div class=\"header\">\r\n");
      out.write("		<div class=\"logo\">\r\n");
      out.write("			<img alt=\"댕냥일기\"  src=\"/resources/css_image/logo.png\"  class=\"logo-img\">\r\n");
      out.write("			<span class=\"logo-text\">댕냥일기</span>\r\n");
      out.write("		</div>		\r\n");
      out.write("		<nav>\r\n");
      out.write("			<a href=\"/member/joinFrm.kh\" class=\"nav-text\">회원가입</a>\r\n");
      out.write("			<a href=\"/\" class=\"nav-text\">로그인</a>\r\n");
      out.write("		</nav>\r\n");
      out.write("	</div>\r\n");
      out.write("</header>\r\n");
      out.write("\r\n");
      out.write("	<div class=\"main\">\r\n");
      out.write("		<div>\r\n");
      out.write("			<img alt=\"메인 이미지\" src=\"/resources/css_image/main.jpg\" class=\"main-image\">\r\n");
      out.write("		</div>\r\n");
      out.write("		\r\n");
      out.write("		<div class=\"index-area\">\r\n");
      out.write("			<div class=\"title-text\">회원가입</div>	\r\n");
      out.write("			\r\n");
      out.write("				<div class=\"join-area\">\r\n");
      out.write("					<form action=\"/member/join.kh\" method=\"post\">\r\n");
      out.write("							<span class=\"tag-name\">ID</span> <span id=\"invalidId\"></span><br>\r\n");
      out.write("							<input type=\"text\" id=\"userId\" name=\"userId\" placeholder=\"영어+숫자 5~10글자 이내\"> \r\n");
      out.write("							<button type=\"button\" id=\"idDuplCk\" class=\"join-button\">ID 중복체크</button> <br>\r\n");
      out.write("							\r\n");
      out.write("							<span class=\"tag-name\">Password</span> <span id=\"invalidPw\"></span><br>\r\n");
      out.write("							<input type=\"password\" id=\"userPw\" name=\"userPw\" placeholder=\"영어+숫자 5~16글자 이내\"> <br>\r\n");
      out.write("							\r\n");
      out.write("							<span class=\"tag-name\">Password Confirm</span> <span id=\"confirmCk\"></span><br>\r\n");
      out.write("							<input type=\"password\" id=\"confirmPw\" placeholder=\"동일한 비밀번호 입력\"> <br>\r\n");
      out.write("							\r\n");
      out.write("							<span class=\"tag-name\">Nickname</span> <br>\r\n");
      out.write("							<input type=\"text\" id=\"userNickname\" name=\"userNickname\" placeholder=\"닉네임\"><button type=\"button\" id=\"nickDuplCk\" class=\"join-button\">닉네임 중복체크</button> <br>\r\n");
      out.write("							\r\n");
      out.write("							<span class=\"tag-name\">이름</span> <br>\r\n");
      out.write("							<input type=\"text\" id=\"userName\" name=\"userName\" placeholder=\"ex) 홍길동\"> <br>\r\n");
      out.write("							\r\n");
      out.write("							<span class=\"tag-name\">주소</span> <br>\r\n");
      out.write("							<input type=\"text\" id=\"userAddress\" name=\"userAddress\" placeholder=\"OO시 OO구\"> <br>\r\n");
      out.write("							\r\n");
      out.write("							<span class=\"tag-name\">이메일</span> <span id=\"invalidEmail\"></span> <br>\r\n");
      out.write("							<input type=\"text\" id=\"userEmail\" name=\"userEmail\" placeholder=\"aaa@example.co.kr\"> <br>\r\n");
      out.write("							\r\n");
      out.write("							<span class=\"tag-name\">전화번호</span>  <span id=\"invalidPhone\"></span> <br>\r\n");
      out.write("							<input type=\"text\" id=\"userPhone\" name=\"userPhone\" placeholder=\"010-0000-0000\"><button type=\"button\" id=\"phoneDuplCk\" class=\"join-button\">전화번호 중복체크</button> <br> <br>\r\n");
      out.write("							\r\n");
      out.write("							<input type=\"submit\" value=\"회원가입\" class=\"submit-button\">\r\n");
      out.write("					</form>	\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("		\r\n");
      out.write("		<script>\r\n");
      out.write("		\r\n");
      out.write("		//유효성 검증 변수\r\n");
      out.write("		let idVal = false;\r\n");
      out.write("		let pwVal = false;\r\n");
      out.write("		let pwConfirm = false;\r\n");
      out.write("		let phoneVal = false;\r\n");
      out.write("		let emailVal = false;\r\n");
      out.write("		\r\n");
      out.write("		//중복체크 검증 변수\r\n");
      out.write("		let chkDuplId = false;\r\n");
      out.write("		let chkDuplNick = false;\r\n");
      out.write("		let chkDuplPhone = false;\r\n");
      out.write("				\r\n");
      out.write("		//아이디 형식 검증\r\n");
      out.write("		const userId = $(\"#userId\");\r\n");
      out.write("		const idMessage = $(\"#invalidId\");\r\n");
      out.write("		\r\n");
      out.write("		const idRegExp = /^[a-zA-Z0-9]{5,10}$/;  //5~10 글자 이내\r\n");
      out.write("		\r\n");
      out.write("		userId.on(\"input\", function(){\r\n");
      out.write("			const value = $(this).val();\r\n");
      out.write("			\r\n");
      out.write("			if(idRegExp.test(value)){\r\n");
      out.write("				idMessage.text(\"\");\r\n");
      out.write("				idMessage.css(\"color\", \"\");\r\n");
      out.write("				idVal = true;\r\n");
      out.write("			}else{\r\n");
      out.write("				idMessage.text(\"*ID는 영어+숫자 5~10글자 사이어야 합니다.\");\r\n");
      out.write("				idMessage.css(\"color\",\"red\");\r\n");
      out.write("				idVal = false;\r\n");
      out.write("			}\r\n");
      out.write("		});\r\n");
      out.write("		\r\n");
      out.write("		//비밀번호 형식 검증\r\n");
      out.write("		const userPw = $(\"#userPw\");\r\n");
      out.write("		const pwMessage = $(\"#invalidPw\");\r\n");
      out.write("		\r\n");
      out.write("		const pwRegExp = /^[a-zA-Z0-9]{5,16}$/;  //5~16 글자 이내\r\n");
      out.write("		\r\n");
      out.write("		userPw.on(\"input\", function(){\r\n");
      out.write("			const value = $(this).val();\r\n");
      out.write("			\r\n");
      out.write("			if(pwRegExp.test(value)){\r\n");
      out.write("				pwMessage.text(\"\");\r\n");
      out.write("				pwMessage.css(\"color\", \"\");\r\n");
      out.write("				pwVal = true;\r\n");
      out.write("			}else{\r\n");
      out.write("				pwMessage.text(\"*비밀번호는 영어+숫자 5~16글자 사이어야 합니다.\");\r\n");
      out.write("				pwMessage.css(\"color\",\"red\");\r\n");
      out.write("				pwVal = false;\r\n");
      out.write("			}\r\n");
      out.write("		});\r\n");
      out.write("				\r\n");
      out.write("		//비밀번호 확인\r\n");
      out.write("		const confirmPw = $(\"#confirmPw\");\r\n");
      out.write("		const confirmCk = $(\"#confirmCk\");\r\n");
      out.write("		confirmPw.on(\"input\",function(){\r\n");
      out.write("			\r\n");
      out.write("			if(confirmPw.val() == userPw.val()){\r\n");
      out.write("				confirmCk.text(\"비밀번호 확인완료\");\r\n");
      out.write("				confirmCk.css(\"color\", \"gray\");\r\n");
      out.write("				pwConfirm = true;\r\n");
      out.write("			}else{\r\n");
      out.write("				confirmCk.text(\"비밀번호가 일치하지 않습니다.\");\r\n");
      out.write("				confirmCk.css(\"color\", \"red\");\r\n");
      out.write("				pwConfirm = false;\r\n");
      out.write("			}		\r\n");
      out.write("		});\r\n");
      out.write("	\r\n");
      out.write("		//전화번호\r\n");
      out.write("		const userPhone = $(\"#userPhone\");\r\n");
      out.write("		const phMessage =$(\"#invalidPhone\");\r\n");
      out.write("		\r\n");
      out.write("		const phRegExp = /^0\\d{2}-\\d{3,4}-\\d{4}$/;  //전화번호 패턴\r\n");
      out.write("		\r\n");
      out.write("		userPhone.on(\"input\", function(){\r\n");
      out.write("			const value = $(this).val();\r\n");
      out.write("			\r\n");
      out.write("			if(phRegExp.test(value)){\r\n");
      out.write("				phMessage.text(\"\");\r\n");
      out.write("				phMessage.css(\"color\", \"\");\r\n");
      out.write("				phoneVal = true;\r\n");
      out.write("			}else{\r\n");
      out.write("				phMessage.text(\"올바른 형식이 아닙니다.\");\r\n");
      out.write("				phMessage.css(\"color\",\"red\");\r\n");
      out.write("				phoneVal = false;\r\n");
      out.write("			}\r\n");
      out.write("		});\r\n");
      out.write("		\r\n");
      out.write("		//이메일\r\n");
      out.write("		const userEmail = $(\"#userEmail\");\r\n");
      out.write("		const emMessage =$(\"#invalidEmail\");\r\n");
      out.write("		\r\n");
      out.write("		const emRegExp = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$/;  //이메일 패턴\r\n");
      out.write("		\r\n");
      out.write("		userEmail.on(\"input\", function(){\r\n");
      out.write("			const value = $(this).val();\r\n");
      out.write("			\r\n");
      out.write("			if(emRegExp.test(value)){\r\n");
      out.write("				emMessage.text(\"\");\r\n");
      out.write("				emMessage.css(\"color\", \"\");\r\n");
      out.write("				emailVal = true;\r\n");
      out.write("			}else{\r\n");
      out.write("				emMessage.text(\"올바른 형식이 아닙니다.\");\r\n");
      out.write("				emMessage.css(\"color\",\"red\");\r\n");
      out.write("				emailVal = false;\r\n");
      out.write("			}\r\n");
      out.write("		});\r\n");
      out.write("		\r\n");
      out.write("		//버튼 클릭시 -> 중복검사 진행(아이디, 닉네임, 전화번호)\r\n");
      out.write("		//중복체크값 저장필요(무결성 검사)\r\n");
      out.write("		\r\n");
      out.write("		const userNickname = $(\"#userNickname\");\r\n");
      out.write("		\r\n");
      out.write("		\r\n");
      out.write("		//아이디\r\n");
      out.write("		$(\"#idDuplCk\").on(\"click\",function(){\r\n");
      out.write("			\r\n");
      out.write("			//입력값이 없는 경우\r\n");
      out.write("			if(!userId.val()){\r\n");
      out.write("				alert(\"아이디를 입력해주세요\");\r\n");
      out.write("				return;\r\n");
      out.write("			}\r\n");
      out.write("			\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"/member/idDuplChk.kh\", //중복검사 서블릿\r\n");
      out.write("				data :{userId : userId.val()},\r\n");
      out.write("				type : \"GET\",				\r\n");
      out.write("				success : function(res) {				\r\n");
      out.write("					 \r\n");
      out.write("					if(res == '1'){\r\n");
      out.write("						alert(\"이미 사용중인 아이디입니다.\")\r\n");
      out.write("						chkDuplId = false;\r\n");
      out.write("					}else{\r\n");
      out.write("						alert(\"사용 가능한 아이디입니다.\")\r\n");
      out.write("						chkDuplId = true;\r\n");
      out.write("					}\r\n");
      out.write("				},\r\n");
      out.write("				error : function () {\r\n");
      out.write("					console.error(\"ajax 요청 실패!\")\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("		});\r\n");
      out.write("		\r\n");
      out.write("		//닉네임\r\n");
      out.write("		$(\"#nickDuplCk\").on(\"click\",function(){\r\n");
      out.write("			\r\n");
      out.write("			//입력값이 없는 경우\r\n");
      out.write("			if(!userNickname.val()){\r\n");
      out.write("				alert(\"닉네임을 입력해주세요\");\r\n");
      out.write("				return;\r\n");
      out.write("			}\r\n");
      out.write("			\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"/member/nickDuplChk.kh\", //중복검사 서블릿\r\n");
      out.write("				data :{ userNickname : userNickname.val()},\r\n");
      out.write("				type : \"GET\",\r\n");
      out.write("				\r\n");
      out.write("				success : function(res) {\r\n");
      out.write("					if(res == '1'){\r\n");
      out.write("						alert(\"이미 사용중인 닉네임입니다.\")\r\n");
      out.write("						chkDuplNick = false;\r\n");
      out.write("					}else{\r\n");
      out.write("						alert(\"사용 가능한 닉네임입니다.\")\r\n");
      out.write("						chkDuplNick = true;\r\n");
      out.write("					}\r\n");
      out.write("				},\r\n");
      out.write("				error : function () {\r\n");
      out.write("					console.error(\"ajax 요청 실패!\")\r\n");
      out.write("				}\r\n");
      out.write("			});\r\n");
      out.write("		});\r\n");
      out.write("		\r\n");
      out.write("		\r\n");
      out.write("		\r\n");
      out.write("		//전화번호\r\n");
      out.write("		$(\"#phoneDuplCk\").on(\"click\",function(){\r\n");
      out.write("			\r\n");
      out.write("			//입력값이 없는 경우\r\n");
      out.write("			if(!userPhone.val()){\r\n");
      out.write("				alert(\"전화번호를 입력해주세요\");\r\n");
      out.write("				return;\r\n");
      out.write("			}\r\n");
      out.write("			\r\n");
      out.write("			\r\n");
      out.write("			$.ajax({\r\n");
      out.write("				url : \"/member/phoneDuplChk.kh\", //중복검사 서블릿\r\n");
      out.write("				data :{ userPhone : userPhone.val()},\r\n");
      out.write("				type : \"GET\",\r\n");
      out.write("				\r\n");
      out.write("				success : function(res) {\r\n");
      out.write("					if(res == '1'){\r\n");
      out.write("						alert(\"이미 가입된 전화번호입니다.\")\r\n");
      out.write("						chkDuplPhone = false;\r\n");
      out.write("					}else{\r\n");
      out.write("						alert(\"사용 가능한 전화번호입니다.\")\r\n");
      out.write("						chkDuplPhone = true;\r\n");
      out.write("						}\r\n");
      out.write("					},\r\n");
      out.write("					error : function () {\r\n");
      out.write("						console.error(\"ajax 요청 실패!\")\r\n");
      out.write("					}\r\n");
      out.write("				});\r\n");
      out.write("			});\r\n");
      out.write("		\r\n");
      out.write("		\r\n");
      out.write("		//제출 버튼 클릭 시 데이터 유효성 검사\r\n");
      out.write("		const userAddr = $(\"#userAddress\");\r\n");
      out.write("		const userName = $(\"#userName\");\r\n");
      out.write("		\r\n");
      out.write("		$(\"form\").on(\"submit\",function(event){\r\n");
      out.write("			\r\n");
      out.write("		//기본 제출 동작 방지\r\n");
      out.write("		event.preventDefault();\r\n");
      out.write("		\r\n");
      out.write("		//빈칸 검사 - 배열 저장\r\n");
      out.write("		   const inputVal = [\r\n");
      out.write("	        { field: userId, message: \"아이디를 입력하세요.\" },\r\n");
      out.write("	        { field: userPw, message: \"비밀번호를 입력하세요.\" },\r\n");
      out.write("	        { field: confirmPw, message: \"비밀번호 확인을 입력하세요.\" },\r\n");
      out.write("	        { field: userNickname, message: \"닉네임을 입력하세요.\" },\r\n");
      out.write("	        { field: userName, message: \"이름을 입력하세요.\" },\r\n");
      out.write("	        { field: userAddr, message: \"주소를 입력하세요.\" },\r\n");
      out.write("	        { field: userEmail, message: \"이메일을 입력하세요.\" },\r\n");
      out.write("	        { field: userPhone, message: \"전화번호를 입력하세요.\" }\r\n");
      out.write("   		 ];\r\n");
      out.write("		\r\n");
      out.write("		//유효성 변수\r\n");
      out.write("		let isValid = false;\r\n");
      out.write("		\r\n");
      out.write("		//빈값 확인\r\n");
      out.write("		for(let i=0; i<inputVal.length; i++){	\r\n");
      out.write("			if(!inputVal[i].field.val().trim()){\r\n");
      out.write("				alert(inputVal[i].message);\r\n");
      out.write("				isValid = false;\r\n");
      out.write("				return;\r\n");
      out.write("			}		  \r\n");
      out.write("			isValid = true;\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		// 중복 검사 확인\r\n");
      out.write("	    if(!chkDuplId) {\r\n");
      out.write("	        alert(\"아이디 중복검사를 완료해주세요.\");\r\n");
      out.write("	        return;\r\n");
      out.write("	    	}\r\n");
      out.write("	    if (!chkDuplNick) {\r\n");
      out.write("	        alert(\"닉네임 중복검사를 완료해주세요.\");\r\n");
      out.write("	        return;\r\n");
      out.write("	    	}\r\n");
      out.write("	    if (!chkDuplPhone) {\r\n");
      out.write("	        alert(\"전화번호 중복검사를 완료해주세요.\");\r\n");
      out.write("	        return;\r\n");
      out.write("	    	}\r\n");
      out.write("	    \r\n");
      out.write("		//데이터 유효 검사\r\n");
      out.write("		isValid = idVal && \r\n");
      out.write("				  pwVal && \r\n");
      out.write("				  pwConfirm && \r\n");
      out.write("				  phoneVal && \r\n");
      out.write("				  emailVal && \r\n");
      out.write("				  chkDuplId && \r\n");
      out.write("				  chkDuplNick && \r\n");
      out.write("				  chkDuplPhone;\r\n");
      out.write("		\r\n");
      out.write("		//제출 - 유효성 검사가 끝난 후 \r\n");
      out.write("		if(isValid){			\r\n");
      out.write("			this.submit();\r\n");
      out.write("		}else{\r\n");
      out.write("		 	alert(\"유효하지 않은 입력값이 있습니다. 다시 확인해주세요.\")\r\n");
      out.write("		 	return;\r\n");
      out.write("		}\r\n");
      out.write("		\r\n");
      out.write("		});\r\n");
      out.write("		\r\n");
      out.write("		</script>\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
