package edu.uestc.blog.controller.admin;

import java.io.File;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import edu.uestc.blog.entity.Blogger;
import edu.uestc.blog.service.BloggerService;
import edu.uestc.blog.utils.Const;
import edu.uestc.blog.utils.DateUtil;
import edu.uestc.blog.utils.MD5Util;
import edu.uestc.blog.utils.ResponseUtil;
import net.sf.json.JSONObject;

/**
 * 博主控制器
 * @author 张霄鹏
 *
 */
@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {
	
	@Resource
	private BloggerService bloggerService ;

	/**
	 * 保存用户信息
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/save")
	public String save(Blogger blogger,MultipartFile imageFile,
			HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {
		//1.首先将图片上传到服务器上
		if(!imageFile.isEmpty()) {
			//1.1获得根路径
			String filePath = request.getServletContext().getRealPath("/") ;
			//1.2生成文件名并拼上其扩展名
			String imageName = DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1] ;
			//1.3上传文件
			imageFile.transferTo(new File(filePath+"/static/userImages/"+imageName));
			//1.4将文件名放到blogger中
			blogger.setImageName(imageName) ;
			//打印出图片地址
			System.out.println(filePath+"/static/userImages/"+imageName);
		}
		
		//2.调用Service层的方法更新博主信息
		int resultTotal = bloggerService.update(blogger) ;
		
		//3.将数据写到前端
		StringBuffer result = new StringBuffer() ;
		if(resultTotal > 0) {
			result.append("<script language='javascript'>alert('修改成功');</script>") ;
		}else{
			result.append("<script language='javascript'>alert('修改失败');</script>") ;
		}
		ResponseUtil.write(response, result) ;
		
		return null ;
	}
	
	/**
	 * 博主的JSON格式的信息，主要用于获得图片
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/find")
	public String find(HttpServletResponse response) throws IOException {
		//1.查询出blogger对象
		Blogger blogger = (Blogger) SecurityUtils.getSubject().getSession().getAttribute(Const.CURRENT_USER) ;
		//2.将博主中的图片写到Json中
		JSONObject jsonObject = JSONObject.fromObject(blogger) ;
		ResponseUtil.write(response, jsonObject);
		
		return null ;
	}
	
	/**
	 * 修改密码
	 * @param id
	 * @param password
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String id,String password1,HttpServletResponse response) throws IOException {
		//1.处理获取的数据
		Blogger blogger = new Blogger() ;
		blogger.setId(Integer.parseInt(id)) ;
		blogger.setPassword(MD5Util.MD5(password1, "java")) ;
		//2.调用service层的方法修改密码
		int resultTotal = bloggerService.update(blogger) ;
		//3.将数据写到Json中
		JSONObject result = new JSONObject() ;
		if(resultTotal > 0) {
			result.put("success", Boolean.valueOf(true)) ;
		}else {
			result.put("success", Boolean.valueOf(false)) ;
		}
		ResponseUtil.write(response, result) ;
		
		return null ;
	}
	
	/**
	 * 用户退出
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		//调用Shiro提供的方法进行退出
		SecurityUtils.getSubject().logout();
		return "redirect:/login.jsp" ;
	}
}
