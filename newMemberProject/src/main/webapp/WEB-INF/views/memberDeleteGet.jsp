<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="include/header.jsp" />
<script>
	function loadFnc() {
		var ok = confirm('진짜 삭제하시겠습니까 ?');
		if (ok == true) {
			document.getElementById('delete').submit();
		} else {
			window.location.href = 'list';
		}
	};
	window.onload = loadFnc;
</script>
<form id='delete' action='delete' method='post'>
<input type='text' name='mno' value='${memberVO.mno }'>
</form>
<jsp:include page="include/footer.jsp" />