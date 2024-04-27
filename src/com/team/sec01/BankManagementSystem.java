package com.team.sec01;
import java.util.ArrayList;
import java.util.Scanner;

// AccountManagement 인터페이스를 구현하는 BankManagementSystem 클래스
public class BankManagementSystem implements AccountManagement {
    private ArrayList<Account> accounts; // 계좌 목록을 저장하는 ArrayList
    private ArrayList<Customer> customers; // 고객 목록을 저장하는 ArrayList

    // BankManagementSystem 생성자
    public BankManagementSystem() {
        accounts = new ArrayList<>(); // 계좌 목록 ArrayList 초기화
        customers = new ArrayList<>(); // 고객 목록 ArrayList 초기화
    }

    // AccountManagement 인터페이스의 추상 메서드 구현

    // 계좌 추가 메서드: 주어진 Account 객체를 accounts 리스트에 추가
    @Override
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // 모든 계좌 정보 출력 메서드
    @Override
    public void printAllAccounts() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    // 계좌 번호로 계좌 검색 메서드: 주어진 계좌 번호에 해당하는 계좌를 찾아서 반환
    @Override
    public Account findAccountById(String accountId) {
        for (Account account : accounts) {
            if (account.getAccountId().equals(accountId)) {
                return account;
            }
        }
        return null; // 계좌를 찾지 못한 경우 null 반환
    }

    // 계좌 입금 메서드: 주어진 계좌 번호에 해당하는 계좌에 입금
    @Override
    public void deposit(String accountId, double amount) {
        Account account = findAccountById(accountId); // 계좌 검색
        if (account != null) {
            account.deposit(amount); // 입금 수행
            System.out.println("입금 완료");
        } else {
            System.out.println("해당 계좌를 찾을 수 없습니다.");
        }
    }

    // 계좌 출금 메서드: 주어진 계좌 번호에 해당하는 계좌에서 출금
    @Override
    public void withdraw(String accountId, double amount) {
        Account account = findAccountById(accountId); // 계좌 검색
        if (account != null) {
            account.withdraw(amount); // 출금 수행
            System.out.println("출금 완료");
        } else {
            System.out.println("해당 계좌를 찾을 수 없습니다.");
        }
    }

    // 고객 추가 메서드: 주어진 Customer 객체를 customers 리스트에 추가
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // 메인 메서드: 프로그램 실행 진입점
    public static void main(String[] args) {
        BankManagementSystem bank = new BankManagementSystem(); // BankManagementSystem 객체 생성
        Scanner scanner = new Scanner(System.in); // Scanner 객체 생성

        // 사용자 입력을 받아 은행 관리 시스템을 실행
        while (true) {
            System.out.println("1. 계좌 생성");
            System.out.println("2. 모든 계좌 출력");
            System.out.println("3. 계좌 입금");
            System.out.println("4. 계좌 출금");
            System.out.println("5. 종료");
            System.out.print("선택: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 입력 버퍼 비우기

            switch (choice) {
                case 1:
                    // 고객 정보 입력
                    System.out.print("고객 ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("고객 이름: ");
                    String customerName = scanner.nextLine();
                    // 고객 추가
                    bank.addCustomer(new Customer(customerId, customerName));

                    // 계좌 정보 입력
                    System.out.print("계좌 번호: ");
                    String accountId = scanner.nextLine();
                    System.out.print("잔액: ");
                    double balance = scanner.nextDouble();
                    // 계좌 생성 및 추가
                    bank.addAccount(new Account(accountId, new Customer(customerId, customerName), balance));
                    break;
                case 2:
                    // 모든 계좌 출력
                    bank.printAllAccounts();
                    break;
                case 3:
                    // 입금할 계좌 정보 입력
                    System.out.print("입금할 계좌 번호: ");
                    String depositAccountId = scanner.nextLine();
                    System.out.print("입금할 금액: ");
                    double depositAmount = scanner.nextDouble();
                    // 입금 실행
                    bank.deposit(depositAccountId, depositAmount);
                    break;
                case 4:
                    // 출금할 계좌 정보 입력
                    System.out.print("출금할 계좌 번호: ");
                    String withdrawAccountId = scanner.nextLine();
                    System.out.print("출금할 금액: ");
                    double withdrawAmount = scanner.nextDouble();
                    // 출금 실행
                    bank.withdraw(withdrawAccountId, withdrawAmount);
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    System.exit(0); // 프로그램 종료
                    break;
                default:
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
}
