package com.walmart.services;

import java.util.List;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Product;

public interface ProductServiceInterface {

	public int addProduct(Product p)throws StockException;
	public int editProdcut(int qty,double price, int pid)throws StockException;
	public int removeProduct(int pid)throws StockException;
	public Product getProdcutById(int id)throws StockException;
	public List<Product> getAll()throws StockException;
}
