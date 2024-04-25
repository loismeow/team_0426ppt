package com.team.sec01;

public class Account {
    private String accountId;
    private Customer customer;
    private double balance;

    public Account(String accountId, Customer customer, double balance) {
        this.accountId = accountId;
        this.customer = customer;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("잔액이 부족합니다.");
        }
    }

    @Override
    public String toString() {
        return "계좌번호: " + accountId +
                ", 고객 ID: " + customer.getCustomerId() +
                ", 고객 이름: " + customer.getName() +
                ", 잔액: " + balance;

    }
}