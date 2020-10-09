package com.boot.crud.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.crud.entity.Customer;
import com.boot.crud.repository.CustomerRepository;

/**
 * @author Anirudha Pawar
 */

@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepo;
	
	@GetMapping("/hello")
	public String getHelloPage() {
		return "Hello";
	}
	
	@GetMapping("/getCustomerList")
	public String getCustomerList(Model model) {
		List<Customer> customerList = (List<Customer>) customerRepo.findAll();
		model.addAttribute("customerList", customerList);
		return "customer-list";
	}
	
	@GetMapping("/showForm")
	public String showFormForAdd(Model theModel) {
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer", theCustomer);
		return "customer-form";
	}
	
	/* this method uses server side validations using @Valid attribute 
	 * Visit Customer class for more details about server side validations*/ 
	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer theCustomer, BindingResult result,Model theModel) {
		if(result.hasErrors()) {
			Customer customer = new Customer();
			theModel.addAttribute("customer", theCustomer);
			return "customer-form";
		} else {
			customerRepo.save(theCustomer);
			return "redirect:/getCustomerList";
		}
	}
	
	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
									Model theModel) {
		Customer customer = customerRepo.getOne(theId);
		theModel.addAttribute("customer", customer);
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		customerRepo.deleteById(theId);
		return "redirect:/getCustomerList";
	}
	
	@GetMapping("/getCountryByCode")
	public String getCountryByCode() {
		return "country";
	}
	
}
