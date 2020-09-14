package com.hk.member.controller;

import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hk.member.service.MemberService;
import com.hk.member.dto.MemberVO;

@RestController
@RequestMapping(value="/member/rest", produces="text/plain; charset=UTF-8")
public class RestMemberController {
      private static final Logger logger = LoggerFactory.getLogger(RestMemberController.class);

      @Autowired
      MemberService memberService;
      
      @GetMapping("/list")
      public String memberRestList(Locale locale, Model model) {
         logger.info("/member/rest/list ----------");
         return "이건 서버에서 보내준 /member/rest/list";
      }
      
      @GetMapping(path = "/listJson", produces = MediaType.APPLICATION_JSON_VALUE)
      public List<MemberVO> memberRestListJson(Locale locale, Model model) {
         logger.info("/member/rest/listJson ----------");
         return memberService.memberList();
      }   
      @GetMapping(path = "/listXML", produces = MediaType.APPLICATION_XML_VALUE)
      public List<MemberVO> memberRestListXML(Locale locale, Model model) {
    	  return memberService.memberList();
      }
      @GetMapping(path="/addJson" , produces = MediaType.APPLICATION_JSON_VALUE)
      public List<MemberVO> memberRestAddJSon(@RequestParam("name") String name , @RequestParam("pwd") String pwd) { 
         logger.info("-----------------");
         logger.info("Client에서 보내온 값은 === " + name + ":::" + pwd);
         
         return memberService.memberList();
      }
      @GetMapping(path="/checkIdDup" , produces = MediaType.APPLICATION_JSON_VALUE)
      public String checkIdDup(@RequestParam("email") String email) { 
      
         
         logger.info("-----------------");
         logger.info("Client에서 보내온 값은 === " + email);
         // client에서 보내온 email을 바탕으로 
         // Service 에서 있는지 체크해보고... 
         // 있으면 ???
         // 없으면 ??? 
         // 그냥 return 하면 200OK인데.. text를 구분해서 보내야 하나?
         MemberVO member = memberService.checkIdDup(email);
         if (member == null) { 
            // 못찾았음... login과 반대
            return "0"; // 못찾았음.
         } else { 
            return "1"; // 찾았음.. 중복임.. 아이디 바꿔야함.
         }
      }
//      @GetMapping(path = "/listXml", produces = MediaType.APPLICATION_XML_VALUE)
//      public List<MemberVO> memberRestXml(Locale locale, Model model) {
//         logger.info("/member/rest/listXml ----------");
//         return memberService.memberList();
//      }   
}