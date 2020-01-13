package com.tyss.stastics.demo.service;

import java.util.Date;
import java.util.List;

import com.tyss.stastics.demo.dto.Products_Info;
import com.tyss.stastics.demo.dto.Sales;

public interface DemoService {

	public Products_Info addProduct(Products_Info productInfo);
	
	public List<Products_Info> getProduct();;
	
	public boolean addToSales(Sales sales,int productid);
	
	public List<Sales> getSales();
	
	public List<Products_Info> checkProducts();
	
	public List<Products_Info> rangeDate(Date start,Date end);
	
	public List<Products_Info> monthlyStastics();
	
	public List<Products_Info> quartelyStastics();

}
