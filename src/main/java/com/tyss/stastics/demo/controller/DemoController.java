package com.tyss.stastics.demo.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.tyss.stastics.demo.dto.Products_Info;
import com.tyss.stastics.demo.dto.Sales;
import com.tyss.stastics.demo.dto.demoResponse;
import com.tyss.stastics.demo.service.DemoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	private DemoService service;

	demoResponse response = new demoResponse();

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor editor = new CustomDateEditor(format, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@PostMapping(path = "/addproduct",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public demoResponse addProduct(@RequestBody Products_Info products) {
		if (service.addProduct(products) != null) {
			response.setStatusCode(200);
			response.setMessage("Product has been registered successfully");
		} else {
			response.setStatusCode(400);
			response.setMessage("Adding Product  failed");
		}
		return response;
	}

	@GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public demoResponse getproducts() {
		List<Products_Info> listproducts = service.getProduct();
		if (listproducts != null) {
			response.setStatusCode(200);
			response.setMessage("got the list of products");
			response.setProduct(listproducts);
			;
		} else {
			response.setStatusCode(200);
			response.setMessage("deatils not got,some bad request");
		}
		return response;
	}
	
	@GetMapping(path="/getnotsold",produces = MediaType.APPLICATION_JSON_VALUE)
	public demoResponse checknotSales() {
	List<Products_Info> notsoldProduct = service.checkProducts();
	if(notsoldProduct!=null) {
		response.setStatusCode(200);
		response.setMessage("product which are not sold list");
		response.setProduct(notsoldProduct);
	}else {
		response.setStatusCode(400);
		response.setMessage("products are not getting");
	}
	return response;
	}
	
	
	@PostMapping(path="/sales/{productid}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public demoResponse addToSales(@RequestBody Sales sales, @PathVariable int productid) {
		if(service.addToSales(sales, productid)) {
			response.setStatusCode(200);
			response.setMessage("posted successfully");
		}else {
			response.setStatusCode(400);
			response.setMessage("Invalid data not able to post");
		}
		return response;
	}
	
	@GetMapping(path="/sales",produces = MediaType.APPLICATION_JSON_VALUE)
	public demoResponse getsales() {
		List<Sales> listsales=service.getSales();
		if(listsales!=null) {
			response.setStatusCode(200);
			response.setMessage("got the list of Sales");
			response.setSoldProduct(listsales);
		}else {
			response.setStatusCode(400);
			response.setMessage("deatils not got,some bad request");
		}	
		return response;
	}
	
	@GetMapping(path="/daterange/{start}/{end}",produces = MediaType.APPLICATION_JSON_VALUE)
	public demoResponse getProductsRange(@PathVariable Date start,@PathVariable Date end) {
		System.out.println(start);
		System.out.println(end);
		List<Products_Info> listsale=service.rangeDate(start, end);
		if(listsale!=null) {
			response.setStatusCode(200);
			response.setMessage("got the list of Sales");
			response.setProduct(listsale);
//			response.setProduct2(listsale);
		}else {
			response.setStatusCode(400);
			response.setMessage("deatils not got,some bad request");
		}	
		return response;
	}
	
	@GetMapping(path="/monthlystastics",produces = MediaType.APPLICATION_JSON_VALUE)
	public demoResponse getMonthlyStastics() {
		List<Products_Info> listsale=service.monthlyStastics();
		if(listsale!=null) {
			response.setStatusCode(200);
			response.setMessage("got the list of Sales");
			response.setProduct(listsale);
		}else {
			response.setStatusCode(400);
			response.setMessage("deatils not got,some bad request");
		}	
		return response;
	}
	
	@GetMapping(path="/quarterlystastics",produces = MediaType.APPLICATION_JSON_VALUE)
	public demoResponse getquarterlyStastics() {
		List<Products_Info> listsale=service.quartelyStastics();
		if(listsale!=null) {
			response.setStatusCode(200);
			response.setMessage("got the list of Sales");
			response.setProduct(listsale);
		}else {
			response.setStatusCode(400);
			response.setMessage("deatils not got,some bad request");
		}	
		return response;
	}
	
	
	
}
