package edu.uestc.blog.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.uestc.blog.entity.Link;
import edu.uestc.blog.service.LinkService;
import edu.uestc.blog.utils.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 链接控制器
 * @author 张霄鹏
 *
 */
@Controller
@RequestMapping({"/admin/link"})
public class LinkAdminController {
	
	@Resource
	private LinkService linkService ;

	/**
	 * 链接列表
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
		
		//2.查询链接列表
		List<Link> linkList = linkService.findByMap(map) ;
		//3.查询总共有多少条数据
		Long total = linkService.getTotal(map) ;
		
		//4.将数据写到response中
		JSONObject result = new JSONObject() ;
		JSONArray jsonArray = JSONArray.fromObject(linkList) ;
		result.put("rows", jsonArray) ;
		result.put("total", total) ;//注意rows和total必须用这两个单词，否则显示不出来
		ResponseUtil.write(response, result) ;
		
		return null ;
	}
	/**
	 * 保存与修改链接
	 * 如果传过来了id则为修改链接
	 * 如果没有穿过类id则为添加链接
	 * @throws IOException 
	 */
	@RequestMapping("/save")
	public String save(Link link,HttpServletResponse response) throws IOException {
		//1.调用Service的方法进行
		int resultTotal = 0 ;
		if(link.getId() == null) {
			//1.1如果没有穿过id来，则调用保存方法
			resultTotal = linkService.add(link) ;
		}else {
			//1.2如果穿过来了id,则调用修改方法
			resultTotal = linkService.update(link) ;
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
	 *  删除链接
	 * @throws IOException 
	 */
	@RequestMapping("/delete")
	public String delete(String ids,HttpServletResponse response) throws IOException {
		//1.调用Service方法进行删除
		String idsStr[] = ids.split(",") ;
		for(int i = 0 ; i <idsStr.length ; i ++) {
			linkService.delete(Integer.parseInt(idsStr[i])) ;
		}
		
		//2.将结果写到Json中
		JSONObject result = new JSONObject() ;
		result.put("success", Boolean.valueOf(true)) ;
		ResponseUtil.write(response, result);
		
		return null ;
	}
}
