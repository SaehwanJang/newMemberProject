<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp" />
<h1>회원 로그인</h1>
<form action='login' method='post'>
이메일: <input type='text' name='email'><br>
암호: <input type='password' name='pwd'><br>
<input type='submit' value='로그인'>
<input type='reset' value='취소'>
</form>
<jsp:include page="../include/footer.jsp" />
