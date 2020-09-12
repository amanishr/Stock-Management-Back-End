package com.walmart.dao;

import java.util.List;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Product;

public interface ProductDaoInterface {
	
	public int addProduct(Product p);
	public int updateProdcut(int qty,double price, int pid);
	public int deleteProduct(int pid);
	public Product getProdcutById(int id) throws StockException;
	public List<Product> getAll();
}
