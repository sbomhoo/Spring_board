package lab.solo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import lab.solo.vo.UserVO;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SoloInterceptor extends HandlerInterceptorAdapter{

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		System.out.println("SessionCheckInterceptor: preHandler() called");
		HttpSession session = request.getSession();
		String requestURI = request.getRequestURI().toString();
		session.setAttribute("requestURL",requestURI.substring(requestURI.lastIndexOf("/")));//추가해야됨당
		UserVO user = (UserVO)session.getAttribute("user");
		if(user==null){
			System.out.println("Session Check Fail");
			response.sendRedirect("./login.do");
			return false;
		}else{
			System.out.println("Session Check Success");
			return super.preHandle(request, response, handler);
		}
	}

}
