//Implement a thread-safe bank class with the following methods.
//
//        Bank() constructor to initialize the bank class, or you can customize the initial deposit.
//        save_money(save_operation, amount) (saveMoney(amount) in Java, saveMoney(amount, saveOperation) in C++) implements the use of save_operation (Main.saveOperation in Java). in Java, Main.saveOperation in C++) to submit a cash operation to the bank in the amount of amount and assign the return value to account.
//        withdraw_money(withdraw_operation, amount) (withdrawMoney(amount) in Java, withdrawMoney(amount, withdrawOperation) in C++) is implemented using withdraw_operation (Main.withdrawOperation in Java, withdrawOperation in C++) to submit a cash withdrawal operation to the bank in the amount of amount and assign the return value to account.
//        check_account() (checkAccount() in Java/C++) returns the amount of money deposited in the bank account.
//        The save_operation (Main.saveOperation in Java, saveOperation in C++) and withdraw_operation (Main.withdrawOperation in Java, withdrawalOperation in Java and Main.withdrawOperation in C++) require the following parameters, in order.
//
//        parameters	description
//        account	the current amount of the account to be modified
//        amount	the amount to be changed
//        Your implementation's bank will be accessed by 2 threads at the same time. One thread uses save_money (saveMoney in Java/C++) to deposit money, and one thread uses withdraw_money (withdrawMoney in Java/C++) to withdraw money, make sure there is enough money in the account when you withdraw it, otherwise you must wait until there is enough money before you can withdraw it. . After you finish accessing the money, we will check if the amount you implemented for the bank is correct with the check_account (checkAccount in Java/C++) method, which will be called after each test case is completed.

//Bank(0)
//        save(50)
//        withdraw(60)
//        save(20)
//        withdraw(10)
//
//        0

import java.util.concurrent.locks.*;

public class Bank {
    private volatile int account;
    private Lock lock;
    private Condition condition ;
    // write your code

    public Bank(int account) {
        this.account = account;
        // write your code

        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public void saveMoney(int amount) throws Exception {

        try{
            lock.lock();
            account = Main.saveOperation(account,amount);
            if(account>=amount){
                condition.signalAll();
            }

        }finally{
            lock.unlock();
        }

    }

    public void withdrawMoney(int amount) throws Exception {
        // write your code

        try{
            lock.lock();
            while(amount>account){
                condition.await();
            }
            account = Main.withdrawOperation(account,amount);

        }finally{
            lock.unlock();
        }
    }

    public int checkAccount(){
        // write your code
        return account;
    }
}
