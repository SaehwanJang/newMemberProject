package com.hk.member.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hk.member.dto.MemberVO;

public class MyInterceptor extends HandlerInterceptorAdapter {

   public boolean preHandle(HttpServletRequest request, HttpServletResponse response,   Object obj ) throws Exception {

      System.out.println("-------------- MyInterCeptor - preHandle");

      // 여기서 로그인 체크
      // session 객체를 가져옴
      HttpSession session = request.getSession();

      // login처리를 담당하는 사용자 정보를 담고 있는 객체를 가져옴
      MemberVO loginMember = (MemberVO) session.getAttribute("loginMember");

      if ( loginMember == null ){
         // 로그인이 안되어 있는 상태임으로 로그인 폼으로 다시 돌려보냄(redirect)
         response.sendRedirect("/auth/login");
         return false; // 더이상 컨트롤러 요청으로 가지 않도록 false로 반환함
      }
      return true;
   }
}