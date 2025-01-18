import java.util.ArrayList;
import java.util.Scanner;

public class ATM implements ATMAc {
    private ArrayList<Account> accounts;
    private ArrayList<Manager> managers;

    public static void main(String[] args) {
        boolean isManagerCreate = false;
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        //กำหนดอัตราแลกเปลี่ยน BTC
        System.out.print("Please enter BTC rate => ");
        int change = scanner.nextInt();
        scanner.nextLine();
        System.out.print("1 BTC has a value of "+ change +" baht.\n");
        //สร้าง Manager
        System.out.print("Create the manager (Y/N) : ");
        String create = scanner.nextLine();
        if (create.equalsIgnoreCase("y")) {
            isManagerCreate = true;
            System.out.print("Enter Manager ID Card : ");
            String manaIdCard = scanner.nextLine();
            System.out.print("Enter Manager Name : ");
            String manaName = scanner.nextLine();
            System.out.print("Enter Manager Gender (Male/Female) : ");
            String manaGender = scanner.nextLine();
            System.out.print("Enter Manager Account ID : ");
            String manaUser = scanner.nextLine();
            System.out.print("Enter Manager Account Password : ");
            String manaPassword = scanner.nextLine();

            Manager manager = new Manager(manaIdCard,manaName,manaGender,manaUser,manaPassword);
            atm.addManagerAccount(manager);
            System.out.println("Manager account created successfully!!!");

            //ล็อกอิน Manager
            System.out.println("Manager Log in to use ATM");
            System.out.print("Manager ID : ");
            String liId = scanner.nextLine();
            System.out.print("Account Password : ");
            String liPassword = scanner.nextLine();
            if (manager.checkLogin(liId,liPassword)) {
                System.out.println("Login successful!!!");
                //สร้างบัญชีธนาคาร
                System.out.print("Step 1. Enter the number of accounts to create : ");
                int accountCount = scanner.nextInt();
                scanner.nextLine(); 
                for (int i = 1; i <= accountCount; i++) {
                    System.out.println("Creating account #" + i);
                    System.out.print("ID Card : ");
                    String acIdCard = scanner.nextLine();
                    System.out.print("Name : ");
                    String acName = scanner.nextLine();
                    System.out.print("Gender (Male/Female) : ");
                    String acGender = scanner.nextLine();
                    System.out.print("Account ID : ");
                    String acId = scanner.nextLine();
                    System.out.print("Password : ");
                    String acPassword = scanner.nextLine();
                    System.out.print("Initial Balance BTC or bath : ");
                    String word = scanner.nextLine();
                    double coin;
                    double balance;
                    if(word.equalsIgnoreCase("BTC")){
                        System.out.print("Initial Balance : ");
                        coin = scanner.nextInt();
                        coin = coin*change;
                    }else{
                        System.out.print("Initial Balance : ");
                        coin = scanner.nextInt();
                    }balance = coin;
                    scanner.nextLine();

                    Account account = new Account(acIdCard, acName, acGender, acId, acPassword, balance, change);
                    atm.addAccount(account);
                    System.out.println("Account added successfully!!!");
                }
                
                boolean running = true;
                while (running) {
                    System.out.println("Please log in to an account to use ATM services.");
                    System.out.print("Enter Account ID : ");
                    String acId = scanner.nextLine();
                    System.out.print("Enter Account Password : ");
                    String acPassword = scanner.nextLine();

                    Account loggedInAccount = atm.findAccountById(acId);
                    if (loggedInAccount != null && loggedInAccount.checkLogin(acId,acPassword)) {
                        System.out.println("Login successful! Welcome, " + loggedInAccount.getNameAccount());
                        boolean login = true;

                        
                        while (login) {
                            System.out.println("\nATM Menu :");
                            System.out.println("1. Check Balance");
                            System.out.println("2. Deposit Money");
                            System.out.println("3. Withdraw Money");
                            System.out.println("4. Transfer Money");
                            System.out.println("5. Exit");

                            System.out.print("Choose : ");
                            int choice = scanner.nextInt();
                            scanner.nextLine(); 

                            switch (choice) {
                                case 1:
                                    System.out.println("Checking balance");
                                    atm.checkable(loggedInAccount);
                                    break;
                                case 2:
                                    System.out.print("Want to deposit BTC or baht : ");
                                    String Dps = scanner.nextLine();
                                    System.out.print("Enter amount to deposit : ");
                                    double depositAmount = scanner.nextDouble();
                                    scanner.nextLine(); 
                                    System.out.println("Depositing money");
                                    atm.depositable(loggedInAccount, depositAmount, Dps);
                                    break;
                                case 3:
                                    System.out.print("Want to withdraw BTC or baht : ");
                                    String Wd = scanner.nextLine();
                                    System.out.print("Enter amount to withdraw : ");
                                    double withdrawAmount = scanner.nextDouble();
                                    scanner.nextLine(); 
                                    System.out.println("Withdrawing money");
                                    atm.withdrawable(loggedInAccount, withdrawAmount, Wd);
                                    break;
                                case 4:
                                    System.out.print("Enter recipient Account ID : ");
                                    String recipientId = scanner.nextLine();
                                    System.out.print("Enter amount to transfer : ");
                                    int transferAmount = scanner.nextInt();
                                    scanner.nextLine(); 

                                    Account recipientAccount = atm.findAccountById(recipientId);
                                    if (recipientAccount != null) {
                                        System.out.println("Transferring money");
                                        atm.transferable(loggedInAccount, recipientAccount, transferAmount);
                                    } else {
                                        System.out.println("Recipient account not found. Transfer failed.");
                                    }
                                    break;
                                case 5:
                                    login = false;
                                    System.out.println("Thank you for using the ATM.");
                                    break;
                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }
                        }
                    }
                }
            } else {
                System.out.println("Login failed. Exiting system.");
            }
        } else {
            System.out.println("Manager not created. Exiting system.");
        }

        scanner.close();
    }

    public ATM() {
        this.accounts = new ArrayList<>();
        this.managers = new ArrayList<>();
    }

    public Account findAccountById(String accountId) {
        for (Account account : accounts) {
            if (account.getIdAccount().equals(accountId)) {
                return account;
            }
        }
        return null;
    }

    @Override
    public void addManagerAccount(Manager account) {
        this.managers.add(account);
    }

    @Override
    public void addAccount(Account account) {
        this.accounts.add(account);
    }


    @Override
    public boolean checkable(Account loginAccount) {
        if (loginAccount == null) {
            System.out.println("No account is currently logged in.");
            return false;
        }
        System.out.println("Account : " + loginAccount.getNameAccount() + ", Balance : " + loginAccount.getMoney() + " bath or " + loginAccount.getMoneybtc() + " BTC.");
        return true;
    }

    
    @Override
    public boolean withdrawable(Account loginAccount, double amount, String wordWd) {
        if (loginAccount == null) {
            System.out.println("No account is currently logged in.");
            return false;
        }
        if (amount > 0 && loginAccount.getMoney() >= amount) {
            if(wordWd.equalsIgnoreCase("BTC")){
                loginAccount.withdrawBtc(amount);
                }else{
                    loginAccount.withdraw(amount);
                }
            return true;
        }
        System.out.println("Withdrawal failed. Insufficient funds or invalid amount.");
        return false;
    }

    
    @Override
    public boolean depositable(Account loginAccount, double amount, String wordDps) {
        if (loginAccount == null) {
            System.out.println("No account is currently logged in.");
            return false;
        }
        if (amount > 0) {
            if(wordDps.equalsIgnoreCase("BTC")){
            loginAccount.depositBtc(amount);
            }else{
                loginAccount.deposit(amount);
            }
            return true;
        }
        System.out.println("Deposit failed. Invalid amount.");
        return false;
    }

    
    @Override
    public boolean transferable(Account loginAccount, Account recipient, int amount) {
        if (loginAccount == null) {
            System.out.println("No account is currently logged in.");
            return false;
        }
        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return false;
        }
        if (amount > 0 && loginAccount.getMoney() >= amount) {
            loginAccount.withdraw(amount);
            recipient.deposit(amount);
            System.out.println("Transfer successful. Transferred " + amount + " to " + recipient.getNameAccount());
            return true;
        }
        System.out.println("Transfer failed. Insufficient funds or invalid amount.");
        return false;
    }
}
