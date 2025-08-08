package org.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecuteBankAccount {


    public void execute() {
           for (int i = 0; i < 3; i++) {
                BankAccount account = new BankAccount();
                account.withdraw(400);
            }
       
    }

    public void executeWithThreads(){
       
            for (int i = 0; i < 3; i++) {
               BankAccount account = new BankAccount();
                account.withdraw(400);
            }
        

    }
}