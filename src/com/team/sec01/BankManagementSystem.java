package com.team.sec01;
import java.util.ArrayList;
import java.util.Scanner;

public class BankManagementSystem implements AccountManagement {
    private ArrayList<Account> accounts;
    private ArrayList<Customer> customers;

    public BankManagementSystem() {
        accounts = new ArrayList<>();
        customers = new ArrayList<>();
    }

    @Override
    public void addAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public void printAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("생성된 계좌가 없습니다.");
        } else {
            for (Account account : accounts) {
                System.out.println(account);
            }
        }
    }

    @Override
    public Account findAccountById(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public void deposit(String accountId, double amount) {
        Account account = findAccountById(accountId);
        if (account != null) {
            account.deposit(amount);
            System.out.println("입금 완료");
        } else {
            System.out.println("해당 계좌를 찾을 수 없습니다.");
        }
    }

    @Override
    public void withdraw(String accountId, double amount) {
        Account account = findAccountById(accountId);
        if (account != null) {
            account.withdraw(amount);
            System.out.println("출금 완료");
        } else {
            System.out.println("해당 계좌를 찾을 수 없습니다.");
        }
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public static void main(String[] args) {
        BankManagementSystem bank = new BankManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. 계좌 생성");
            System.out.println("2. 모든 계좌 출력");
            System.out.println("3. 계좌 입금");
            System.out.println("4. 계좌 출금");
            System.out.println("5. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 버퍼 비우기

            switch (choice) {
                case 1:
                    System.out.print("고객 ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("고객 이름: ");
                    String customerName = scanner.nextLine();
                    bank.addCustomer(new Customer(customerId, customerName));

                    System.out.print("계좌 번호: ");
                    String accountId = scanner.nextLine();
                    System.out.print("잔액: ");
                    double balance = scanner.nextDouble();
                    bank.addAccount(new Account(accountId, new Customer(customerId, customerName), balance));
                    break;
                case 2:
                    bank.printAllAccounts();
                    break;
                case 3:
                    System.out.print("입금할 계좌 번호: ");
                    String depositAccountId = scanner.nextLine();
                    System.out.print("입금할 금액: ");
                    double depositAmount = scanner.nextDouble();
                    bank.deposit(depositAccountId, depositAmount);
                    break;
                case 4:
                    System.out.print("출금할 계좌 번호: ");
                    String withdrawAccountId = scanner.nextLine();
                    System.out.print("출금할 금액: ");
                    double withdrawAmount = scanner.nextDouble();
                    bank.withdraw(withdrawAccountId, withdrawAmount);
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
}