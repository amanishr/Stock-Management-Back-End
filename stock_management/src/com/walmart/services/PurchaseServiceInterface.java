package com.walmart.services;

import java.util.List;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Purchase;

public interface PurchaseServiceInterface {

	public int addPurchase(int purchaseId, int supplierId, int productId, int quantity, String status)throws StockException;
	public int updatePurchase(String status, int pid)throws StockException;
	public Purchase getPurchaseById(int id)throws StockException;
	public List<Purchase> getAll()throws StockException;
}
