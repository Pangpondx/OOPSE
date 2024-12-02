import java.util.ArrayList;
import java.util.Scanner;

public class ATM {
    private ArrayList<Account> accounts;
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

       // เพิ่มบัญชี
       System.out.print("Step 1. Enter amount of all account = ");
       int accountCount = scanner.nextInt();
       scanner.nextLine(); // Consume leftover newline

       System.out.println("Step 2. Enter Detail of each account.");
       for (int i = 1; i <= accountCount; i++) {
           System.out.println("No." + i);

           String id;
           while (true) {
               System.out.print("Account ID = ");
               id = scanner.nextLine();
               if (id.length() == 13) {
                   break;
               }
               System.out.println("Error: Account ID must be 13 digits. Please try again.");
           }

           String name;
           while (true) {
               System.out.print("Account Name = ");
               name = scanner.nextLine();
               if (name.length() <= 50) {
                   break;
               }
               System.out.println("Error: Account Name must not exceed 50 characters. Please try again.");
           }

           String password;
           while (true) {
               System.out.print("Password = ");
               password = scanner.nextLine();
               if (password.length() == 4) {
                   break;
               }
               System.out.println("Error: Password must be 4 digits. Please try again.");
           }

           int money;
           while (true) {
               System.out.print("Balance = ");
               money = scanner.nextInt();
               scanner.nextLine(); // Consume leftover newline
               if (money <= 1000000) {
                   break;
               }
               System.out.println("Error: Balance must not exceed 1,000,000. Please try again.");
           }

           atm.addAccount(new Account(name, id, password, money));
       }

       // เริ่มต้นระบบ ATM
       atm.start();
    }
    public ATM() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    // ฟังก์ชันลดเงิน (ถอนเงิน)
    public void withdraw(Account account, int amount) {
        if (account.checkMoney(amount)) {
            System.out.println("Withdrawal successful. Remaining balance: " + account.getMoney());
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    // เริ่มต้นระบบ ATM
    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nATM ComputerThanyaburi Bank");
        System.out.print("Account ID : ");
        String loginId = scanner.nextLine();

        System.out.print("Account Password : ");
        String loginPass = scanner.nextLine();

        // ตรวจสอบข้อมูลบัญชี
        boolean isAuthenticated = false;
        Account loggedInAccount = null;

        for (Account account : this.accounts) {
            if (account.verifyID(loginId) && account.verifyPassword(loginPass)) {
                System.out.println("Login Success");
                isAuthenticated = true;
                loggedInAccount = account;
                break;
            }
        }

        if (isAuthenticated) {
            boolean exit = false;
            while (!exit) {
                System.out.println("\nATM ComputerThanyaburi Bank");
                System.out.println("Account ID : "+loginId);
                System.out.println("Menu Service");
                System.out.println("1. Account Balance");
                System.out.println("2. Withdrawal");
                System.out.println("3. Exit");
                System.out.print("Choose : ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Your balance is: " + loggedInAccount.getMoney());
                        break;

                    case 2:
                        System.out.print("Enter amount to withdraw: ");
                        int amount = scanner.nextInt();
                        withdraw(loggedInAccount, amount); // เรียกใช้ฟังก์ชันถอนเงิน
                        break;

                    case 3:
                        System.out.println("Thank you for using the ATM.");
                        exit = true;
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } else {
            System.out.println("Login Failed");
        }
        scanner.close();
    }
}