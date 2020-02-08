package edu.uestc.Travel.web.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@SuppressWarnings("serial")
public class BaseServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//完成方法的分发
		//1.获取请求路径
		String uri = request.getRequestURI() ;
		//2.获取方法名称
		String methodName = uri.substring(uri.lastIndexOf("/")+1) ;
		//3.获取方法对象Method		
		try {
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//4.执行方法
			method.invoke(this, request,response) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * 直接将传入的对象序列化为json,并且写回客户端
	 */
	public void writeValue(Object object,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper() ;
		response.setContentType("application/json;charset=utf-8") ;
		mapper.writeValue(response.getOutputStream(), object);
	}
	/*
	 * 将传入的对象序列化为json,返回
	 */
	public String writeValueAsString(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper() ;
		String json = mapper.writeValueAsString(object) ;
		return json ;
	}
	
}
