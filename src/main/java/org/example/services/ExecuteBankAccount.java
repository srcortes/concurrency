package org.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ExecuteBankAccount {

    @Autowired
    private SynchronizedBankAccount synchronizedAccount;

    @Autowired
    private UnsynchronizedBankAccount unsynchronizedAccount;

 

    private static final int THREAD_COUNT = 2;
    private static final int WITHDRAWAL_COUNT = 3;

    public void executeSynchronized() {
        runConcurrentWithdrawals(synchronizedAccount);
    }

    public void executeUnsynchronized() {
        runConcurrentWithdrawals(unsynchronizedAccount);
    }

  
    private void runConcurrentWithdrawals(Object account) {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        
        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.submit(() -> {
                for (int j = 0; j < WITHDRAWAL_COUNT; j++) {
                    int amount = 400; // Random amount between 10 and 50
                    if (account instanceof SynchronizedBankAccount) {
                        ((SynchronizedBankAccount) account).withdraw(amount);
                    } else if (account instanceof UnsynchronizedBankAccount) {
                        ((UnsynchronizedBankAccount) account).withdraw(amount);
                    }
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final balance: " + getBalance(account));
    }

    private int getBalance(Object account) {
        if (account instanceof SynchronizedBankAccount) {
            return ((SynchronizedBankAccount) account).getBalance();
        } else if (account instanceof UnsynchronizedBankAccount) {
            return ((UnsynchronizedBankAccount) account).getBalance();
        } 
        return -1;
    }
}
