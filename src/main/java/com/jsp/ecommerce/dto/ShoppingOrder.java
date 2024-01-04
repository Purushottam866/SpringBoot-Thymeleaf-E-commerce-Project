package com.jsp.ecommerce.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class ShoppingOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	String order_id;
	String payment_id;
	private LocalDateTime dateTime;
	private double amount;

	@OneToMany(fetch = FetchType.EAGER)
	List<Item> items;

	@ManyToOne
	Customer customer;

	public String getDate() {
		return this.dateTime.format(DateTimeFormatter.ofPattern("dd-MMM-YYYY hh:mm"));
	}
}
