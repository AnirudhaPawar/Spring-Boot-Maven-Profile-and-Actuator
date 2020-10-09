package com.boot.crud.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TimeoutThreadService {

	private ExecutorService executor = Executors.newFixedThreadPool(10);
    private RestTemplate restTemplate = new RestTemplate();

    public String getData() {
        Future<String> future = executor.submit(new Task(restTemplate));
        String response = null;

        try {
            response = future.get(100, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(response);
        return response;
    }
}
