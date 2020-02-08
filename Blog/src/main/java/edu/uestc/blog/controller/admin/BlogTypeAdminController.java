package edu.uestc.blog.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.uestc.blog.entity.BlogType;
import edu.uestc.blog.service.BlogService;
import edu.uestc.blog.service.BlogTypeService;
import edu.uestc.blog.utils.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 博客类型控制器
 * @author 张霄鹏
 *
 */
@Controller
@RequestMapping({"/admin/blogType"})
public class BlogTypeAdminController {
	
	@Resource
	private BlogTypeService blogTypeService ;
	@Resource
	private BlogService blogService ;
	/**
	 * 博客类型列表
	 * @thpageSize IOException 
	 */
	@RequestMapping({"/list"})//注意rows和page必须用这两个单词，否则显示不出来
	public String list(String page,String rows,HttpServletResponse response) throws IOException {
		
		//PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows)) ;
		//1.将接收到的数据类型处理一下，并存储到map集合中
		int pageNum = Integer.parseInt(page) ;
		int rowsNum = Integer.parseInt(rows) ;
		int start = (pageNum - 1) * rowsNum ;
		Map<String,Object> map = new HashMap<String,Object>() ;
		map.put("start",start) ;
		map.put("pageSize",rowsNum) ;
		
		//2.查询博客类型列表
		List<BlogType> blogTypeList = blogTypeService.findByMap(map) ;
		//3.查询总共有多少条数据
		Long total = blogTypeService.getTotal(map) ;
		
		//4.将数据写到response中
		JSONObject result = new JSONObject() ;
		JSONArray jsonArray = JSONArray.fromObject(blogTypeList) ;
		result.put("rows", jsonArray) ;
		result.put("total", total) ;//注意rows和total必须用这两个单词，否则显示不出来
		ResponseUtil.write(response, result) ;
		
		return null ;
	}
	/**
	 * 保存与修改博客类型
	 * 如果传过来了id则为修改博客类型
	 * 如果没有穿过类id则为添加博客类型
	 * @throws IOException 
	 */
	@RequestMapping("/save")
	public String save(BlogType blogType,HttpServletResponse response) throws IOException {
		//1.调用Service的方法进行
		int resultTotal = 0 ;
		if(blogType.getId() == null) {
			//1.1如果没有穿过id来，则调用保存方法
			resultTotal = blogTypeService.add(blogType) ;
		}else {
			//1.2如果穿过来了id,则调用修改方法
			resultTotal = blogTypeService.update(blogType) ;
		}
		//2.将结果写到Json中
		JSONObject result = new JSONObject() ;
		if(resultTotal > 0) {
			result.put("success", Boolean.valueOf(true)) ;
		}else {
			result.put("success", Boolean.valueOf(false)) ;
		}
		ResponseUtil.write(response, result);
		
		return null ;
	}
	
	/**
	 *  删除数据类型
	 * @throws IOException 
	 */
	@RequestMapping("/delete")
	public String delete(String ids,HttpServletResponse response) throws IOException {
		//1.调用Service方法进行删除
		String idsStr[] = ids.split(",") ;
		
		JSONObject result = new JSONObject() ;
		
		for(int i = 0 ; i <idsStr.length ; i ++) {
			//1.1在删除之前，判断该类型有没有博客
			Map<String,Object> map = new HashMap<String,Object> () ;
			map.put("typeId", idsStr[i]) ;
			if(blogService.getTotal(map) > 0) {
				result.put("exist", "该博客类型下有博客，不能删除！") ;
			}else{
				blogTypeService.delete(Integer.parseInt(idsStr[i])) ;
			}
		}
		
		//2.将结果写到Json中
		result.put("success", Boolean.valueOf(true)) ;
		ResponseUtil.write(response, result);
		
		return null ;
	}
}
