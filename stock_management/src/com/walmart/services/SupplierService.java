package com.walmart.services;

import java.sql.SQLException;
import java.util.List;

import com.walmart.dao.SupplierDao;
import com.walmart.exceptions.StockException;
import com.walmart.pojo.Supplier;

public class SupplierService implements SupplierServiceInterface {

	SupplierDao supplierDao;
	
	public SupplierService() throws SQLException{
		supplierDao=new SupplierDao();
	}
	@Override
	public int addSupplier(Supplier s) throws StockException {
		
		int i=0;
		if(s.getSid()>0 && s.getSid()< 99999) {
			if(s.getSname().matches("[a-zA-z]{3,25}")) {
				i=supplierDao.addSupplier(s);
			}else {
				throw new StockException("Invalid Name");
			}
		}else {
			throw new StockException("Invalid Id");
		}	
		return i;
	}

	@Override
	public int editSupplier(double rating, int sid) throws StockException {

		return supplierDao.updateSupplier(rating, sid);
	}

	@Override
	public int removeSupplier(int sid) throws StockException {
		
		return supplierDao.deleteSupplier(sid);
	}

	@Override
	public Supplier getSupplierById(int id) throws StockException {

		return supplierDao.getSupplierById(id);
	}

	@Override
	public List<Supplier> getAll() throws StockException {

		List<Supplier> suppliers=supplierDao.getAll();
		if(suppliers.isEmpty()) {
			throw new StockException("No Supplier available");
		}
		return suppliers;
	}

}
