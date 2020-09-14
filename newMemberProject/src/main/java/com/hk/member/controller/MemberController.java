package com.hk.member.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hk.member.dto.MemberVO;
import com.hk.member.service.MemberService;

@Controller
public class MemberController {
	   private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
		//1) @Controller Annotation 추가(import org.springframework.stereotype.Controller;)
		//2) URL설정 (Get or Post or 둘다)
		//	Get만 지정하는 방법 @GetMapping
		//	Post만 지정하는 방법 @PostMapping
		//	둘다 지정하는 방법 @RequestMapping
		// /member/list -> Full URL은 http://location:9999/member/list
		// URL 체계가 : /member  /list,/register/delete/update
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ServletContext sc;
	
	@RequestMapping(value="/member/list",method = RequestMethod.GET)//포스트까지 하려면method = {RequestMethod.GET,RequestMethod.POST}
	//약식은 @GetMapping("/memeber/list")  //method = RequestMethod.GET = getmapping하겠다는 뜻
	public String memberList(Locale locale,Model model) {
		
		//3) DB접속이 필요한가? (Service모듈 Repository(DAO), DTO 필요)
		//	 DB접속 없이 화면이 필요한가?
		//	 DB접속도 화면도 필요없나?? ->RestFul + Ajax(지금 대상은 아님)
		
		// DB접속이 필요하다면
		// Service 모듈 : Repository에 있는 이름과 동일하게 작성/별도의 트랜잭션/ 테이블2개 이상 사용하여 연결하는 작업이 없음.
		// Repository 모듈 : -Maybatis를 사용
		// 					-pom.xml에 관련 package를 설정(spring-jdbc, spring-mybatis,mybatis,hikari Datasource등등..)
		//					-root-context.xml에서 db관련 설정
		//					-상세 sql logging을 보려면 별도의 package를 설정
		//					-Mapper interface 만들기
		//					-Mapper.xml에 sql 만들기
		
		// DB JDBC DRIVER : Ojdbc6 or Ojdbc8(Oracle 11G XE) 
		// Spring-JDBC(Spring 버전을 따라감)
		// myBatis-JDBC
		// mybatis-spring
		// Hikari CP(JDBC 성능향상 Datasource DBCP2)
		// log4jdbc(SQL을 실행하게되면 log4j에 나오게 해주는 클래스)
		
		// DTO : taBLE하고 1:1 Mapping(getter/setter를 편하게 만들게 하기위해 lombok이라는 별도의 class사용)
		
		//Service를 호출 List<MemberVO>를 일반변수에는 담을 필요가 없다. DB에서 읽어온 내용은 JSP에서 출력하기위해
		//JSP에서 값을 꺼내가려면 .. sc,session,request라는 곳에 저장을 해두면 꺼낼수 있었는데 지금 만들 controller는
		// servlet이 아니어서 꺼낼수가 없음. 그래서 spring에서는 model이라는 이름으로 저장하고 꺼낼수 있는걸 지원함.
		logger.info("Welcome home! The client locale is {}.", locale);
		model.addAttribute("members",memberService.memberList());
		return "memberList";
		//"WEB-ING/views/memberList.jsp"
		//"redirect:/member/list
	}
	@GetMapping("/member/register")
	public String memberRegisterGet(Model model) {
		// 사용자가 입력을 할 수 있도록 Html만 보내준다.
		logger.info("/member/register 호출");
		return "memberRegister";
	}
	@PostMapping("/member/register")
	public String memberRegisterPost(Model model, MemberVO memberVO) {
		
		logger.info("/member/registerPost 호출");
		
		logger.info("사용자에게 입력받은 값= "+ memberVO.toString());
		memberService.memberRegister(memberVO);
		model.addAttribute("name",memberVO.getMname());
		return "memberRegisterDone";
			
	}
	@GetMapping("/member/update")
	public String memberUpdateGet(@RequestParam("mno") int mno,Model model) {
		model.addAttribute("memberVO",memberService.memberGetOne(mno));
		return "memberUpdateGet";
	}
	@PostMapping("/member/update")
	public String memberUpdatePost(MemberVO memberVO,Model model) {
		memberService.memberUpdate(memberVO);
		model.addAttribute("memberVO",memberVO);
		return "memberUpdatePost";
	}
	@GetMapping("/member/delete")
	public String memberDeleteGet(@RequestParam("mno")int mno,Model model) {
		model.addAttribute("memberVO",memberService.memberGetOne(mno));
		return "memberDeleteGet";
	}
	@PostMapping("/member/delete")
	public String memberDeletePost(MemberVO memberVO,Model model) {
		memberService.memberDelete(memberVO); //  
		model.addAttribute("memberVO",memberVO);// 
		return "memberDeletePost";
	}
	   @RequestMapping(value = "/upload", method = RequestMethod.POST , headers = "content-type=multipart/*")
	   //@PostMapping("/upload")
//	   public String upload(@RequestParam("file") MultipartFile multipartFile,Model model) {
//	      logger.info("### upload");
//	      logger.info("실제 파일이름은 ? " + multipartFile.getOriginalFilename());
//	      
//	      File targetFile = new File(sc.getRealPath("/resources/fileupload/") + multipartFile.getOriginalFilename());
//
//	      logger.info("파일 저장위치는 :  " + targetFile);
//	      try {
//	         InputStream fileStream = multipartFile.getInputStream();
//	        // FileUtils.copyInputStreamToFile(fileStream, targetFile);
//	         FileUtils.copyInputStreamToFile(fileStream, targetFile);
//	      } catch (IOException e) {
//	         FileUtils.deleteQuietly(targetFile);
//	         e.printStackTrace();
//	      }
//	      
//	      // 실제 디렉토리와 URL은 다르다.. 
//	      // URL은 http://localhost:9999/resources/fileupload/실제파일명
//	      // model에 담아서 jsp에서 img로 출력 
//	      
//	      model.addAttribute("imgSrc", "/resources/fileupload/" + multipartFile.getOriginalFilename());
//	      return "upload";
//	   }
	   public String upload(@RequestParam("file") MultipartFile multipartFile,Model model) {
		      logger.info("### upload");
		      logger.info("실제 파일이름은 ? " + multipartFile.getOriginalFilename());
		      
		      File targetFile = new File(sc.getRealPath("/resources/fileupload/") + multipartFile.getOriginalFilename());

		      logger.info("파일 저장위치는 :  " + targetFile);
		      InputStream inputStream = null;
		      OutputStream outputStream = null;

		      try {
		         // upload된 stream을 받아서 targetFile로 저장 

		         inputStream = multipartFile.getInputStream();
		         outputStream = new FileOutputStream(targetFile);
		         
		         int fileByte = 0;
		         while((fileByte = inputStream.read()) != -1 ) {
		            outputStream.write(fileByte);
		         }
		         
		         //FileUtils.copyInputStreamToFile(fileStream, targetFile);
		      } catch (IOException e) {
		         //FileUtils.deleteQuietly(targetFile);
		         
		         // 복사하다가 disk full등 에러나면 ? 
		         // 우선 패스
		         e.printStackTrace();
		      } finally { 
		         try {
		            inputStream.close();
		         } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		         }
		         try {
		            outputStream.close();
		         } catch (IOException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		         }
		      }
		      
		      // 실제 디렉토리와 URL은 다르다.. 
		      // URL은 http://localhost:9999/resources/fileupload/실제파일명
		      // model에 담아서 jsp에서 img로 출력 
		      
		      model.addAttribute("imgSrc", "/resources/fileupload/" + multipartFile.getOriginalFilename());
		      return "upload";
		   }
		
	
}