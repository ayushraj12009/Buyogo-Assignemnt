package com.Buyogo.Buyogo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class controller
{
    @GetMapping("/hi")
    public String hellp(){
        return "this is working";
    }

}
