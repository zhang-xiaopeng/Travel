package edu.uestc.Travel.service;

import java.util.List;

import edu.uestc.Travel.pojo.Category;

public interface CategoryService {
	//查询所有
	public List<Category> findAll() ;
	//根据cid查询cname
	public String findById(String cid);
}
