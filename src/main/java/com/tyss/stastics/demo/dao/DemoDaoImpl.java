package com.tyss.stastics.demo.dao;

import java.util.Date;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;


import org.springframework.stereotype.Repository;
import com.tyss.stastics.demo.dto.Products_Info;
import com.tyss.stastics.demo.dto.Sales;


@Repository
public class DemoDaoImpl implements DemoDao {

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public Products_Info addProduct(Products_Info productInfo) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			manager.persist(productInfo);
			transaction.commit();
			return productInfo;
		}catch(Exception e) {
			manager.close();
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Products_Info> getProduct() {
		EntityManager manager = factory.createEntityManager();
		String jpql = "From Products_Info";
		Query query = manager.createQuery(jpql);
		List<Products_Info> product = (List<Products_Info>) query.getResultList();
		if (product== null) {
			return null;
		}
		return product;
	}

	@Override
	public boolean addToSales(Sales sales, int productid) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		transaction.begin();
    	sales.setProductInfo(manager.find(Products_Info.class,productid ));
		manager.persist(sales);
		transaction.commit();
		return true;
	}

	@Override
	public List<Sales> getSales() {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		String jpql = "From Sales";
		Query query = manager.createQuery(jpql);
		List<Sales> list = query.getResultList();
		if (list == null) {
			return null;
		}
		return list;
	}

	@Override
	public List<Products_Info> checkProducts() {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		String check="select * from products_info where  productId not in(select productInfo from sales_info group by productInfo)";
		Query query = manager.createNativeQuery(check);
		List<Products_Info> notsales= query.getResultList();
		if(notsales == null) {
			return null;
		}
		return notsales;
	}

	
	@Override
	public List<Products_Info> rangeDate(Date start,Date end) {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		String range = "select * from products_info where productId in (select productInfo from sales_info where sold_Date between :start and :end)";
		Query query = manager.createNativeQuery(range);
		query.setParameter("start",start);
		query.setParameter("end", end);
		List<Products_Info> range1 = query.getResultList();
		if(range1 == null) {
			return null;
		}
		return range1;
		
	}

	@Override
	public List<Products_Info> monthlyStastics() {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		String range = "select  monthname(sold_Date),count(productInfo) from sales_info group by extract(month from sold_Date) ";
		Query query = manager.createNativeQuery(range);
		List<Products_Info> range1 = query.getResultList();
		if(range1 == null) {
			return null;
		}
		return range1;
	}

	@Override
	public List<Products_Info> quartelyStastics() {
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		String salesByQuarter = "select year(sold_Date) as year ,quarter(sold_Date) as quarter, count(productInfo) from sales_info GROUP BY year(sold_Date),quarter(sold_Date) ORDER BY year(sold_Date),quarter(sold_Date)";
		Query query = manager.createNativeQuery(salesByQuarter);
		List<Products_Info> quarter = query.getResultList();
		if(quarter==null) {
			return null;
		}
		return quarter;
	}
		
}

	

	

	
	
	

