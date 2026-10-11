package sandbox;

import sandbox.bank.Account;

public class SavingsAccount extends Account {
    void addInterest(){
        balance = balance * 1.05;
    }

    void steal(Account victim){
        victim.balance = 0;
    }

    void takeAllFrom(SavingsAccount other){
        this.balance = this.balance + other.balance;
        other.balance = 0;
    }

    public static void main (String[] args){
        SavingsAccount my = new SavingsAccount();
        my.addInterest();
        System.out.println(my.getBalance());
        Account friend = new Account();
        my.takeAllFrom(friend);
    }
}
