public class Account extends Person {
    private String id;
    private String password;
    private double money;
    private int btc;
    private double coin;
    
    public Account(String idCard,String name,String gender,String id,String password, double money, int btc){
        super(idCard, name, gender);
        this.id = id;
        this.password = password;
        this.money = money;
        this.btc = btc;
    }

    public double getMoney() {
        return this.money;
    }

    public double getMoneybtc() {
        this.coin = money/btc;
        return this.coin;
    }

    public String getNameAccount(){
        return this.getName();
    }

    public String getIdAccount(){
        return this.id;
    }

    public boolean checkLogin(String liId, String liPassword){
        return this.id.equals(liId) && this.password.equals(liPassword);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.money += amount;
            this.coin += amount/btc;
            System.out.println("Deposit successful. New balance: " + this.money + " bath.");
        } else {
            System.out.println("Deposit failed. Invalid amount.");
        }
    }

    public void depositBtc(double amount) {
        if (amount > 0) {
            this.coin += amount;
            this.money += amount*btc;
            System.out.println("Deposit successful. New balance: " + this.coin + " BTC.");
        } else {
            System.out.println("Deposit failed. Invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= this.money) {
            this.money -= amount;
            this.coin -= amount/btc;
            System.out.println("Withdrawal successful. Remaining balance: " + this.money + " bath.");
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
        }
    }

    public void withdrawBtc(double amount) {
        if (amount > 0 && amount <= this.getMoneybtc()) {
            this.coin -= amount;
            this.money -= amount*btc;
            System.out.println("Withdrawal successful. Remaining balance: " + this.coin + " BTC.");
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
        }
    }
}
