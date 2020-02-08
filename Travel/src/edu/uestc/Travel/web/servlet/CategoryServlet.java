package edu.uestc.Travel.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.uestc.Travel.pojo.Category;
import edu.uestc.Travel.service.CategoryService;
import edu.uestc.Travel.service.impl.CategoryServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet{
	private CategoryService service = new CategoryServiceImpl() ;
	/*
	 * 查询所有
	 */
	public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.调用Service里的方法查询所有
		List<Category> cs = service.findAll() ;
		//2.序列化Json
/*		ObjectMapper mapper = new ObjectMapper() ;
		
		response.setContentType("application/json;charset=utf-8") ;
		mapper.writeValue(response.getOutputStream(), cs);*/
		writeValue(cs,response) ;
	}
	/*
	 * 根据cid查询cname
	 */
	public void findById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid") ;
		String cname = service.findById(cid) ;
		writeValue(cname, response);
	}
}
