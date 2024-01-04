package com.jsp.ecommerce.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.ecommerce.dto.Customer;
import com.jsp.ecommerce.service.CustomerService;
import com.razorpay.RazorpayException;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController 
{
	@Autowired
	Customer customer;
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/signup")
	public String loadSignup(ModelMap map)
	{
		map.put("customer", customer);
		return "SignUp.html";
	}
	
	@GetMapping("/home")
	public String loadHome(HttpSession session, ModelMap map) {
		if (session.getAttribute("customer") != null)
			return "CustomerHome";
		else {
			map.put("fail", "Session Expired, Login Again");
			return "HomePage";
		}
	}
	
	@PostMapping("/signup")
	public String signup(@Valid Customer customer, BindingResult result,ModelMap map)
	{
		if(result.hasErrors())
		{
			return "SignUp.html";
		}
		else
		{
			return customerService.signup(customer,map);
		}
		
	}
	
	@PostMapping("/verify-otp")
	public String verifyOtp(@RequestParam int id,@RequestParam int otp,ModelMap map)
	{
		return customerService.verifyOtp(id,otp,map);
	}
	
	@GetMapping("/fetch-products")
	public String fetchProducts(HttpSession session, ModelMap map) {
		Customer customer=(Customer) session.getAttribute("customer");
		if (customer != null) {			
			return customerService.fetchProducts(map,customer);
		} else {
			map.put("fail", "Session Expired, Login Again");
			return "HomePage";
		}
	}

	@GetMapping("/cart-add/{id}")
	public String addToCart(HttpSession session, ModelMap map, @PathVariable int id) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			return customerService.addToCart(customer, id, map,session);
		} else {
			map.put("fail", "Session Expired, Login Again");
			return "HomePage";
		}
	}
	
	@GetMapping("/fetch-cart")
	public String viewCart(HttpSession session, ModelMap map)
	{
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			return customerService.viewCart(customer,map);
		} else {
			map.put("fail", "Session Expired, Login Again");
			return "HomePage";
		}
	}
	
	@GetMapping("/cart-remove/{id}")
	public String removeFromCart(HttpSession session, ModelMap map, @PathVariable int id) {
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			return customerService.removeFromCart(customer, id, map,session);
		} else {
			map.put("fail", "Session Expired, Login Again");
			return "HomePage";
		}
	}
	
	@GetMapping("/payment")
	public String createOrder(HttpSession session, ModelMap map) throws RazorpayException 
	{
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) 
		{
			return customerService.createOrder(customer, map);
		} 
		else 
		{
			map.put("fail", "Session Expired, Login Again");
			return "HomePage";
		}
	}
	
	@PostMapping("/payment-complete/{id}")
	public String completeOrder(@PathVariable int id,HttpSession session,ModelMap map,@RequestParam String razorpay_payment_id)
	{
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) 
		{
			return customerService.completeOrder(id,razorpay_payment_id,customer, map,session);
		}
		else
		{
			map.put("fail", "Session Expired, Login Again");
			return "HomePage";
		}
	}
	
	@GetMapping("/fetch-orders")
	public String fetchOrders(HttpSession session,ModelMap map)
	{
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			return customerService.fetchAllorder(customer, map);
		} else {
			map.put("fail", "Session Expired, Login Again");
			return "HomePage";
		}
	}
	
	@GetMapping("/order-items/{id}")
	public String getOrderItems(@PathVariable int id,HttpSession session,ModelMap map)
	{
		Customer customer = (Customer) session.getAttribute("customer");
		if (customer != null) {
			return customerService.fetchAllorderItems(id,customer, map);
		} else {
			map.put("fail", "Session Expired, Login Again");
			return "HomePage";
		}
	}
	
}
