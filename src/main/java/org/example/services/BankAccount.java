package org.example.services;

import org.springframework.stereotype.Component;


//Idea for this class is to learn about lock mechanisms in Java
@Component
public class BankAccount {
  private int balance = 1000;

    public void withdraw(int amount) {
    
            if (balance >= amount) {
                System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                balance -= amount;
                System.out.println(Thread.currentThread().getName() + " completed withdrawal. Balance: " + balance);
            } else {
                System.out.println(Thread.currentThread().getName() + " insufficient funds. Balance: " + balance);
            }
        
    }

    public int getBalance() {
        return balance;
    }
}