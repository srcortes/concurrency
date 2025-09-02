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

    @GetMapping("/withdraw/synchronized")
    public String testSynchronized() {
        long startTime = System.currentTimeMillis();
        bankAccount.executeSynchronized();
        long endTime = System.currentTimeMillis();
        return "Synchronized execution completed in " + (endTime - startTime) + " ms";
    }

    @GetMapping("/withdraw/unsynchronized")
    public String testUnsynchronized() {
        long startTime = System.currentTimeMillis();
        bankAccount.executeUnsynchronized();
        long endTime = System.currentTimeMillis();
        return "Unsynchronized execution completed in " + (endTime - startTime) + " ms";
    }

}
