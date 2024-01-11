package com.miyawaki.batchsystem.rest.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miyawaki.batchsystem.rest.api.response.ApiResponse;

@RestController
public class ApiController {
    @GetMapping("/sample")
    public ApiResponse apiMethod() {
        ApiResponse response = new ApiResponse();
        response.setMessage("Hello, this is a sample response!");
        response.setStatusCode(200);
        return response;
    }

    @GetMapping("/")
    public ApiResponse entryPointMethod() {
        ApiResponse response = new ApiResponse();
        response.setMessage("Hello, this is a sample response! entry point");
        response.setStatusCode(200);
        return response;
    }
}
