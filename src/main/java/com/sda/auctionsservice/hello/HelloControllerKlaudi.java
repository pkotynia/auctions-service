package com.sda.auctionsservice.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("helloklaudi")

public class HelloControllerKlaudi {
    @GetMapping
    public String helloklaudi(){
        return "Hello World!";
    }

}
