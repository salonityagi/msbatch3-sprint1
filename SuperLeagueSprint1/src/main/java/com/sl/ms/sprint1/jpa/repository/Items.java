package com.sl.ms.sprint1.jpa.repository;

import java.util.Date;
import java.util.Calendar;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;



@Entity(name = "ITEMS")
public class Items {	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@Column(name = "items_Date")
	private Date itemsDate;

	@Column(name = "name")
	private String name;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "quantity")
	private int quantity;	
	
	
	protected Items() {
		
	}
	
	public Items(Long id, String name, int price, int quantity, Date itemsDate) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.itemsDate = itemsDate;
	}

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public Date getItemsDate() {
		return itemsDate;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", name=" + name + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
	

}
