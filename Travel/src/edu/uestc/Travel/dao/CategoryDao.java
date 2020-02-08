package edu.uestc.Travel.dao;

import java.util.List;

import edu.uestc.Travel.pojo.Category;

public interface CategoryDao {
	//查询所有，列表显示
	public List<Category> findAll() ;
	//根据cid查询cname
	public String findById(int cid);
}
