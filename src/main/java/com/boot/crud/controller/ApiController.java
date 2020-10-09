package com.boot.crud.controller;

import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.boot.crud.entity.Customer;
import com.boot.crud.repository.CustomerRepository;
import com.boot.crud.service.TimeoutThreadService;

/**
 * @author Anirudha Pawar
 */

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private TimeoutThreadService ts;
	
	@GetMapping("/testApi")
	public ResponseEntity<String> testApi() {
        String response = "";
        try {
			response = ts.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getCustomerList")
	public List<Customer> getCustomerList(){
		return (List<Customer>) customerRepo.findAll();
	}
	
	/***** Calling Stored Procedure  *****/
	@GetMapping("/getCustomerList/{custId}")
	public Customer getCustomerList(@PathVariable String custId){
		return customerRepo.callCustomerProc(Integer.parseInt(custId));
	}
	
	@PostMapping("/addCustomer")
	public String addCustomer(@RequestBody Customer customer){
		customerRepo.save(customer);
		return "Success";
	}
	
	/******************** API/Web Service consumption using Spring RestTemplate ********************/
	
	@GetMapping("/getCountry")
	public String getCountry(@RequestParam String code){
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
		      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		      HttpEntity <String> entity = new HttpEntity<String>(headers);
		      String response = restTemplate.exchange("https://restcountries.eu/rest/v2/callingcode/"+code, HttpMethod.GET, entity, String.class).getBody();
		      JSONObject json = new JSONObject(response.substring(1, response.length()-1));
		      System.out.println(json);
		      return json.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "false"; 
		}
	}

}
