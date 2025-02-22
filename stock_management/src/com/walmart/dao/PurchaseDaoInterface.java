package com.walmart.dao;

import java.util.List;

import com.walmart.exceptions.StockException;
import com.walmart.pojo.Purchase;

public interface PurchaseDaoInterface {

	public int addPurchase(Purchase p);
	public int updatePurchase(String status, int purchaseId);
	public Purchase getPurchaseById(int id) throws StockException;
	public List<Purchase> getAll();
}
