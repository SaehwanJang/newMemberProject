<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="include/header.jsp" />
<p>삭제 성공입니다! 5초후 자동으로 리스트로 이동합니다.</p>   
<a href='/member/list'>리스트로 가기</a>
<script>
	setTimeout(function(){window.location='list';},5000);
</script>
<jsp:include page="include/footer.jsp" />
</body>
</html>