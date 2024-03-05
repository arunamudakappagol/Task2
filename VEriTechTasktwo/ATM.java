package VEriTechTasktwo;

import java.util.HashMap;
import java.util.Scanner;

class ATMaccount {
    private HashMap<String, User> users;

    public ATMaccount() {
       
        users = new HashMap<>();
        users.put("123456", new User("123456", "1234", 1000.0));
        users.put("654321", new User("654321", "4321", 500.0));
    }

    public boolean authenticateUser(String userID, String userPIN) {
        User user = users.get(userID);
        return user != null && user.getUserPIN().equals(userPIN);
    }

    public double checkBalance(String userID) {
        return users.get(userID).getAccountBalance();
    }

    public void withdraw(String userID, double amount) {
        User user = users.get(userID);
        if (user != null && user.getAccountBalance() >= amount) {
            user.setAccountBalance(user.getAccountBalance() - amount);
            System.out.println("Withdrawal successful. Remaining balance: " + user.getAccountBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void deposit(String userID, double amount) {
        User user = users.get(userID);
        if (user != null) {
            user.setAccountBalance(user.getAccountBalance() + amount);
            System.out.println("Deposit successful. New balance: " + user.getAccountBalance());
        } else {
            System.out.println("User not found.");
        }
    }
}

public class ATM {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMaccount atm = new ATMaccount();

        System.out.println("Welcome to the ATM.");
        System.out.print("Enter user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String userPIN = scanner.nextLine();

        if (atm.authenticateUser(userID, userPIN)) {
            System.out.println("Authentication is successful.");

            
            System.out.println("1. Check Balance");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Deposit Money");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Current balance: " + atm.checkBalance(userID));
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(userID, withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(userID, depositAmount);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        } else {
            System.out.println("Authentication failed. Invalid credentials.");
        }
    }
}