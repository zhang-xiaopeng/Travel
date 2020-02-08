package edu.uestc.Travel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.uestc.Travel.dao.CategoryDao;
import edu.uestc.Travel.dao.impl.CategoryDaoImpl;
import edu.uestc.Travel.pojo.Category;
import edu.uestc.Travel.service.CategoryService;
import edu.uestc.Travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

public class CategoryServiceImpl implements CategoryService {
	private CategoryDao dao = new CategoryDaoImpl() ;
	
	@Override
	public List<Category> findAll() {
		//1.从redis中查询
		//1.1获取jedis客户端
		Jedis jedis = JedisUtil.getJedis() ;
		//1.2可使用sortedset排序
		//Set<String> categorys = jedis.zrange("category", 0, -1) ;
		//1.3查询sortedset中的分数cid和cname
		Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1) ;
		//2.判断查询的集合是否为空
		List<Category> cs = null ;
		if(categorys == null || categorys.size() == 0 ) {
			//3.如果为空，需要从数据库中查询，将数据存入Redis
			cs = dao.findAll() ;
			for(int i = 0 ; i < cs.size() ; i ++) {
				jedis.zadd("category", cs.get(i).getCid(),cs.get(i).getCname()) ;
			}
		}else {
			//4.如果不为空，将set的数据存入list
			cs = new ArrayList<Category>() ;
			for(Tuple tuple : categorys) {
				Category category = new Category() ;
				category.setCname(tuple.getElement()) ;
				category.setCid((int)tuple.getScore());
				cs.add(category) ;
			}
		}
		
		return cs ;
	}

	@Override
	public String findById(String cid) {
		return dao.findById(Integer.parseInt(cid)) ;
	}

}
