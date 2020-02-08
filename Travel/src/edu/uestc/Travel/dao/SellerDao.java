package edu.uestc.Travel.dao;

import edu.uestc.Travel.pojo.Seller;

public interface SellerDao {
	//根据sid查询卖家信息
	public Seller findById(int sid);
}
