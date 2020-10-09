package com.boot.crud.service;

import java.util.concurrent.Callable;

import org.springframework.web.client.RestTemplate;

class Task implements Callable<String> {

    private RestTemplate restTemplate;

    public Task(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String call() throws Exception {
    	System.out.println("here");
    	String response = "";
        try {
        	String url = "127.0.0.1:8888/api/getCustomerList";
            response = restTemplate.getForObject(url, String.class);
            System.out.println(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return response;
    }
}
