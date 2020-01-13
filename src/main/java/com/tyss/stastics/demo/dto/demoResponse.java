package com.tyss.stastics.demo.dto;


import java.util.List;
import lombok.Data;

@Data
public class demoResponse {

	private int statusCode;
	private String message;
	private boolean status;
	private List<Products_Info> product;
	private List<Sales> soldProduct;
	private Products_Info product1;	
}
