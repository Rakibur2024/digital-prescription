package com.digital.prescription.controllers;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class TestController {
    @GetMapping("/test")
    public String test(){
        System.out.println("Test Method");
        return "Test";
    }

    @GetMapping("/test2")
    public String test2(){
        System.out.println("Test Method 2");
        return "Test 2";
    }

    @PostMapping("/demoPost")
    @PreAuthorize("hasAuthority('SECURITY_WRITE')")
    public String demoPost(){
        return "Demo Post";
    }

    @GetMapping("/test3")
    @PreAuthorize("hasAuthority('SECURITY_READ')")
    public String test3(){
        System.out.println("Test Method 3");
        return "Test 3";
    }

    @GetMapping("/postAuthTest")
    @PostAuthorize("returnObject == 'Test 3' or hasAuthority('SECURITY_WRITE')")
    public String postAuthTest() {
        System.out.println("Inside postAuthTest method...");
        return "Test 3"; // You can change this return value to test
    }
}