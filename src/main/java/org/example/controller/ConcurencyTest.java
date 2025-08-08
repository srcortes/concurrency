package org.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.services.ExecuteBankAccount;

@RestController
@RequestMapping(value = "/concurrency")
public class ConcurencyTest {
   
    private final ExecuteBankAccount bankAccount;

    @Autowired
    public ConcurencyTest(ExecuteBankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }    

    @GetMapping("/withdraw/v1")
    public void sayHello() {
        bankAccount.execute();         
    }

      @GetMapping("/withdraw/v2")
    public void controlThreads() {
        bankAccount.executeWithThreads();;         
    }
}
