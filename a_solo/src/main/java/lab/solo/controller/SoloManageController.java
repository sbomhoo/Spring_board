package lab.solo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.solo.service.SoloManageService;
import lab.solo.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class SoloManageController {
   
   @Autowired
   private SoloManageService service;
  
 
   
   //
	@RequestMapping(value="login.do", method = RequestMethod.GET)
	public String getLoginForm(){
		return "index.jsp";
		
	}
    @RequestMapping(value="/login.do" , method=RequestMethod.POST)
    public ModelAndView requestLogin(@RequestParam("userid")String userid,@RequestParam("userpw")String userpw,HttpServletRequest request){
       ModelAndView mav = new ModelAndView();
       UserVO user = null;
       
       user = service.loginProc(userid,userpw);
       System.out.println(userid);
       System.out.println(userpw);
       System.out.println(service.loginProc(userid,userpw));
      
       if(user!=null){
           HttpSession session = request.getSession();
           session.setAttribute("user", user);
           session.setAttribute("userid", userid);
          mav.setViewName("../../index_LoginSuccess");
          System.out.println("성공!");
       }else{
          mav.setViewName("../../index_Fail");
          System.out.println("실패!");
       }
       return mav;
    }	
   @RequestMapping(value="/join.do", method=RequestMethod.GET)
   public String getJoinPage(){
      return "users/user_form";
      
   }
   
   @RequestMapping(value="/join.do", method=RequestMethod.POST)
   public ModelAndView requestJoin(@ModelAttribute("user")UserVO user,HttpServletResponse response) throws IOException{
      ModelAndView mav=new ModelAndView();
      if(service.joinUser(user)>0){
    	 
         mav.setViewName("redirect:index.jsp");
         PrintWriter writer=response.getWriter();
         writer.println("<script>alert('join Success'); </script>");
      } else{
         PrintWriter writer=response.getWriter();
         writer.println("<script>alert('join fail'); </script>");
      }
      return mav;      
   }
	@RequestMapping(value="login.do", method = RequestMethod.GET)
	public String getLogoutForm(){
		return "index";
		
	}
   @RequestMapping(value="/logout.do")
   public ModelAndView mainLogout(HttpServletRequest request){
       ModelAndView mav =new ModelAndView();
	   HttpSession session = request.getSession();
	   session.invalidate();			//session 종료(안에있는 데이터 다삭제)
		System.out.println("세션해제됬어염뿌우");
		mav.setViewName("../../index");
		return mav;
   }
   @RequestMapping(value="find.do", method=RequestMethod.GET)
   public String getFindPage(){
      return "users/user_change";
      
   }
   @RequestMapping(value="modify.do")
   public ModelAndView requestModifyPage(UserVO user,@RequestParam("userid")String userid){
      ModelAndView mav=new ModelAndView();
      service.modifyUser(user, "userid");
      mav.addObject("user",user);
      System.out.println("컨트롤단에서의 유저에양!: "+user);
      if(user!=null){
          mav.setViewName("../../index_LoginSuccess");
      }else{
          mav.setViewName("message");
      }

      return mav;
   }
   @RequestMapping(value="/idCheck.do" , method=RequestMethod.GET)
   public ModelAndView requestIdCheck(@RequestParam("userid")String userid){
      ModelAndView mav = new ModelAndView();
      UserVO user = null;
      user = service.idCheck(userid);
      System.out.println(userid+": 컨트롤단에서 받아본 유저아이디값");
      System.out.println(service.idCheck(userid)+": 서비스주소인가?");
//      
      if(user!=null){
         mav.setViewName("checkFail");
         System.out.println("중복아이디에염!");
      }else{
         mav.setViewName("checkOk");
         System.out.println("중복아니에염");
      }
      return mav;
   }
}