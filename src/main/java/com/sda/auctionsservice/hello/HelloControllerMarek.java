package com.sda.auctionsservice.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hellomarek")
public class HelloControllerMarek {
    @GetMapping
    public String hellomarek(){
        return "Hello Marek";
    }
}
