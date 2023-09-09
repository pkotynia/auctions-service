package com.sda.auctionsservice.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hellopawel")
public class HelloControllerPawel {

    @GetMapping
    public String hellopawel() {
        return "Hello Paweł";
    }
}
