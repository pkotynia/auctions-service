package com.sda.auctionsservice.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hellopiotr")
public class HelloControllerPiotrek {

    @GetMapping
    public String hellopiotr() {
        return "Hello Piotrek";
    }
}
