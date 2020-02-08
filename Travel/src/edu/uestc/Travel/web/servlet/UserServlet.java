package edu.uestc.Travel.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import edu.uestc.Travel.pojo.ResultInfo;
import edu.uestc.Travel.pojo.User;
import edu.uestc.Travel.service.UserService;
import edu.uestc.Travel.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
	private UserService service = new UserServiceImpl() ;
	//注册Servlet
	public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//验证码校验
		String check = request.getParameter("check") ;
		//从session中获取验证码
		HttpSession session = request.getSession() ;
		String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER") ;
		session.removeAttribute("CHECKCODE_SERVER");  //为了保证验证码只能使用一次
		//比较
		if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
			//验证码错误
			ResultInfo info = new ResultInfo() ;
			info.setFlag(false);
			info.setErrorMsg("验证码错误！");
			
			//将info对象序列化为json
/*			ObjectMapper mapper = new ObjectMapper() ;
			String json = mapper.writeValueAsString(info) ;
			
			//将json数据写回客户端
			//设置ContentType
			response.setContentType("application/json;charset=utf-8") ;
			response.getWriter().write(json);*/
			writeValue(info, response);
			
			return ;
		}
		
		//1.获取数据
		Map<String,String[]> map = request.getParameterMap() ;
		//2.封装对象到User类中
		User user = new User() ;
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//3.调用Service中的方法
		//UserService service = new UserServiceImpl() ;
		boolean flag = service.regist(user) ;
		//4.响应结果
		ResultInfo info = new ResultInfo() ;
		if(flag) {
			//注册成功
			info.setFlag(true);
		}else {
			//注册失败
			info.setFlag(false);
			info.setErrorMsg("注册失败！");
		}
		
		//将info对象序列化为json
/*		ObjectMapper mapper = new ObjectMapper() ;
		String json = mapper.writeValueAsString(info) ;
		
		//将json数据写回客户端
		//设置ContentType
		response.setContentType("application/json;charset=utf-8") ;
		response.getWriter().write(json);*/
		writeValue(info, response);
	}
	//登录Servlet
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//验证码校验
		String check = request.getParameter("check") ;
		//从session中获取验证码
		HttpSession session = request.getSession() ;
		String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER") ;
		session.removeAttribute("CHECKCODE_SERVER");  //为了保证验证码只能使用一次
		//比较
		if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
			//验证码错误
			ResultInfo info = new ResultInfo() ;
			info.setFlag(false);
			info.setErrorMsg("验证码错误！");
			
/*			//将info对象序列化为json
			ObjectMapper mapper = new ObjectMapper() ;
			String json = mapper.writeValueAsString(info) ;
			
			//将json数据写回客户端
			//设置ContentType
			response.setContentType("application/json;charset=utf-8") ;
			response.getWriter().write(json);*/
			
			return ;
		}
		
		//1.获取用户名和密码数据
		Map<String,String[]> map = request.getParameterMap() ;
		//2.将数据封装到User中
		User user = new User() ;
		try {
			BeanUtils.populate(user, map) ;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		//3.调用service中的方法实现登录
		//UserService service = new UserServiceImpl() ;
		User u = service.login(user) ;
		//4.判断用户对象是否为null
		ResultInfo info = new ResultInfo() ;
		if(u == null) {
			//用户名或密码错误
			info.setFlag(false) ;
			info.setErrorMsg("用户名或密码错误");
		}
		//5.判断邮件是否激活
		if(u != null && !"Y".equals(u.getStatus())) {
			//用户尚未激活
			info.setFlag(false) ;
			info.setErrorMsg("您尚未激活，请激活");
		}
		//6.登录成功判断
		if(u != null && "Y".equals(u.getStatus())) {
			//登录成功
			info.setFlag(true) ;
			request.getSession().setAttribute("user", u);
		}
		
		//7.相应数据
/*		ObjectMapper mapper = new ObjectMapper() ;
		String json = mapper.writeValueAsString(info) ;
		response.setContentType("application/json;charset=utf-8") ;
		response.getWriter().write(json);*/
		writeValue(info, response);
	}
	//根据用户名查找用户Servlet
	public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.从session中获取登录用户
		Object user = request.getSession().getAttribute("user") ;
		//2.将user写回客户端
/*		ObjectMapper mapper = new ObjectMapper() ;
		response.setContentType("application/json;charset=utf-8");
		mapper.writeValue(response.getOutputStream(), user) ;*/
		writeValue(user, response);
	}
	//退出Servlet
	public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.销毁session
		request.getSession().invalidate() ;
		//2.跳转到登录页面
		response.sendRedirect(request.getContextPath() + "/login.html") ;
	}
	//激活Servlet
	public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取激活码
		String code = request.getParameter("code") ;
		if(code != null) {
			//2.调用Service完成激活
			//UserService service = new UserServiceImpl() ;
			boolean flag = service.active(code) ;
			//3.判断标记
			String msg ;
			if(flag) {
				//激活成功
				msg = "激活成功，请<a href='../login.html'>登录</a>" ;
			}else {
				//激活失败
				msg = "激活失败，请联系管理员" ;
			}
			response.setContentType("text/html;charset=utf-8") ;
			response.getWriter().write(msg) ;
		}	
	}

}
