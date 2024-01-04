package com.jsp.ecommerce.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Component
@Entity
public class Customer 
{
	@Id
	@GeneratedValue(generator = "slno")
	@SequenceGenerator(initialValue = 101, allocationSize = 1, sequenceName = "slno", name = "slno")
	private int id;
	
	@NotEmpty(message = "This is mandatory")
	private String name;
	
	@NotNull(message = "This is mandatory")
	@DecimalMin(value = "6000000000",message = "Enter proper Mobile Number")
	@DecimalMax(value = "9999999999",message = "Enter proper Mobile Number")
	private long mobile;
	
	@NotEmpty(message = "This is mandatory")
	@Email(message = "Enter Proper Format")
	private String email;
	
	@NotEmpty(message = "This is mandatory")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "*Should Match Pattern")
	private String password;
	
	@Past(message = "Enter Proper Date")
	@NotNull(message = "Select A Date")
	private LocalDate dob;
	
	@NotEmpty(message = "This is mandatory")
	private String address;
	
	private int otp;
	boolean verify;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	ShoppingCart cart; 
	
	
	
}
