package com.acc.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class GatewatController {
    @GetMapping("/order")
    public ResponseEntity<String> orderFallBack(){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).
                body("Order service is down please try again later");
    }
    @GetMapping("/payment")
    public ResponseEntity<String> paymentFallBack(){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).
                body("Payment service is down please try again later");
    }
    @GetMapping("/user")
    public ResponseEntity<String> userFallBack(){
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).
                body("user service is down please try again later");
    }
}
