<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page = "include/header.jsp"/>
<h1> update 결과</h1>
<b>${member.mname}님 정상적으로 수정 되었습니다.</b>
<a href= "list">리스트로 이동하시려면 클릭을 아니면 5초후 자동이동합니다.</a>
<script>
	setTimeout(function() {window.location='list';},5000);
</script>
<jsp:include page="include/footer.jsp"/>