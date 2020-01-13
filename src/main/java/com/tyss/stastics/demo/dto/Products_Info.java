package com.tyss.stastics.demo.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Products_Info")
public class Products_Info {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "productId")
	private int productId;
	
	@Column(name = "product_name")
	private String productName;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "created_Date")
	private Date manufactureDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "expiry_Date")
	private Date expiryDate;
	
	@Column(name = "product_Quantity")
	private int productQuantity;
	
	@Column(name = "net_weight")
	private double netWeight;
	
	@Column(name = "batch_number")
	private long batchNumber;
	
	@Column(name = "status")
	private String status;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productInfo")
	private List<Sales> sales;
}
