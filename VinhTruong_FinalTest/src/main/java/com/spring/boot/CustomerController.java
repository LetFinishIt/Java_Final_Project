package com.spring.boot;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.boot.Customer;
import com.spring.boot.CustomerRepository;


@Controller
public class CustomerController {
	@Autowired
	private CustomerRepository custRepo;
	
	@RequestMapping(value = "/")
	public String home()
	{
		return "index";
    }
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@RequestParam("customerId") int customerId,
		@RequestParam("firstName") String firstName,
		@RequestParam("lastName") String lastName,
		@RequestParam("addressLine1") String addressLine1,
		@RequestParam("addressLine2") String addressLine2,
		@RequestParam("city") String city,
		@RequestParam("state") String state,
		@RequestParam("postalCode") String postalCode,
		@RequestParam("phone") String phone,
		@RequestParam("email") String email,
		@RequestParam("creditLimit") double creditLimit)
	{
		Customer customer = new Customer(customerId,firstName,lastName,addressLine1,addressLine2,
										city,state ,postalCode,phone, email , creditLimit);
		custRepo.save(customer);
		return "thank";
	}
	
	@RequestMapping("/show")
	public String show(Model model)
	{
		model.addAttribute("customer", custRepo.findAll());
		return "show";
	}

	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int no, Model model) {
	    Customer cust= custRepo.findById(no)
	      .orElseThrow(() -> new IllegalArgumentException("Invalid Customer ID:" + no));
	    custRepo.delete(cust);
	    model.addAttribute("customer", custRepo.findAll());
	    return "show";
	}
}
