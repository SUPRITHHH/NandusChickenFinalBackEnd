package com.tyss.stastics.demo.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="sales_Info")
public class Sales {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="sales_Id")
	private int sales_Id;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "sold_Date")
	private Date soldDate;
	@Column(name="productQuantitySold_Info")
	private int productQuantitySold;
	@Column(name="productQuantityUnsold_Info")
	private int productQuantityUnsold ;
	@ManyToOne(cascade =  CascadeType.ALL)
	@JoinColumn(name="productInfo")
	@JsonIgnore
	private Products_Info productInfo;
	
}
