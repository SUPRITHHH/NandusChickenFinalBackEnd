package com.tyss.stastics.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.stastics.demo.dao.DemoDao;
import com.tyss.stastics.demo.dto.Products_Info;
import com.tyss.stastics.demo.dto.Sales;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoDao dao;
	
	@Override
	public Products_Info addProduct(Products_Info productInfo) {
		return dao.addProduct(productInfo);
	}

	@Override
	public List<Products_Info> getProduct() {
		return dao.getProduct();
	}

	@Override
	public boolean addToSales(Sales sales,int productid) {
		return dao.addToSales(sales, productid);
	}

	@Override
	public List<Sales> getSales() {
		return dao.getSales();
	}

	@Override
	public List<Products_Info> checkProducts() {
		return dao.checkProducts();
	}

	@Override
	public List<Products_Info> rangeDate(Date start, Date end) {
		return dao.rangeDate(start, end);
	}

	@Override
	public List<Products_Info> monthlyStastics() {
		return dao.monthlyStastics();
	}

	@Override
	public List<Products_Info> quartelyStastics() {
		return dao.quartelyStastics();
	}

	
	
	
}
