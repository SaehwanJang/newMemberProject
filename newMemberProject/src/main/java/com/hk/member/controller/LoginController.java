package com.hk.member.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.member.service.MemberService;
import com.hk.member.dto.MemberVO;

@Controller
@RequestMapping("/auth")   
public class LoginController {
   private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

   @Autowired
   MemberService memberService;
   
   
   @GetMapping("/login") 
   public String loginGet() { 
      return "auth/loginGet";
   }
   
   @PostMapping("/login") 
   public String loginPost(HttpSession session,MemberVO member) { 
   
      
      logger.info("사용자가 입력한 email / passwd = " + member.toString());
      
   
      MemberVO loginMember = memberService.memberLogin(member);
      if( loginMember == null) { 

         logger.info("로그인 실패");
         return "auth/loginFail";
      } else {
       
         logger.info("로그인 성공");
         session.setAttribute("loginMember", loginMember);
        
         return "redirect:../member/list";  
      }
      
   }
   
   // w이해했습니다...ㅋㅋ
   @RequestMapping(value="/logout", method = { RequestMethod.GET , RequestMethod.POST})
   public String authLogout(HttpSession session) {
      logger.info("LoginController_request_/auth/logout 실행");
      
      session.invalidate();
      
      return "auth/loginGet";
   }
   
   
}