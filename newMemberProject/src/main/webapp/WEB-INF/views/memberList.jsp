<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="include/header.jsp" />
<%-- <c:forEach var="member" items="${members}"> --%>
<%--    ${member.mno } , ${member.email } , ${member.mname } , ${member.cre_date } <br> --%>
<%-- </c:forEach> --%>

<!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

      <!-- Main Content -->
      <div id="content">

        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-2 text-gray-800">FAQ</h1>
          <p class="mb-4">게시물 리스트
          &nbsp;&nbsp;&nbsp;<a href="register">등록</a>
<!--           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/auth/login">로그인</a> -->
          </p>

          <!-- DataTales Example -->
          <div class="card shadow mb-4">

            <div class="card-body">
              <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                  <thead>
                    <tr>
                      <th>number</th>
                      <th>email</th>
                      <th>name</th>
                      <th>작성일</th>
                      <th>수정일</th>
                      <th>수정/삭제</th>
                    </tr>
                  </thead>
                  <tbody>
                  <c:forEach items="${members}" var="memberVO">
                  
                    <tr>
                    <style>
                  #Up_btn{
                  	border-top-left-radius: 5px;
                  	border-bottom-left-radius: 5px;
                  	margin-right:-4px;
                  	border: 1px solid gray;
                  	background-color:rgba(0,0,0,0);
                  	color:gary;
                  	padding: 5px;
                  }
                  #Del_btn{
                  	border-top-right-radius: 5px;
                  	border-bottom-right-radius: 5px;
                  	margin-left:-3px;
                  	border: 1px solid gray;
                  	background-color:rgba(0,0,0,0);
                  	color:gray;
                  	padding: 5px;
                  }
               
                  
                  </style>
                    <!-- <a href='update?mno=<c:out value="${member.mno}" />' > -->
                      <td><c:out value="${memberVO.mno }" /></td>
                      <td><c:out value="${memberVO.email }" /></td>
                      <td><c:out value="${memberVO.mname }" /></td>
                      <td><fmt:formatDate pattern="yyyy-MM-dd" value="${memberVO.cre_date }" /></td>
                      <td><fmt:formatDate pattern="yyyy-MM-dd" value="${memberVO.mod_date }" /></td>
                      <td align="center">               
                      <button id=Up_btn><a href='update?mno=<c:out value="${memberVO.mno}" />' >수정</a></button>
                      <button id=Del_btn><a href='delete?mno=<c:out value="${memberVO.mno}" />' >삭제</a></button></td>
                     
                      
                    </tr>
                    </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

        </div>
        <!-- /.container-fluid -->

      </div>
      <!-- End of Main Content -->

      <!-- Footer -->
      <footer class="sticky-footer bg-white">
        <div class="container my-auto">
          <div class="copyright text-center my-auto">
            <span>Copyright &copy; Your Website 2020</span>
          </div>
        </div>
      </footer>
      <!-- End of Footer -->

    </div>
    <jsp:include page="include/footer.jsp" />
    <!-- End of Content Wrapper -->
