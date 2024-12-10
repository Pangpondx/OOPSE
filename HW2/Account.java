public class Account extends Person {
    private String id;
    private String password;
    private int money;
    
    public Account(String idCard,String name,String gender,String id,String password, int money){
        super(idCard, name, gender);
        this.id = id;
        this.password = password;
        this.money = money;
    }
    // ตรวจสอบว่ามีเงินเพียงพอและลดยอดเงิน
    public boolean checkMoney(int amount) {
        if (amount > 0 && amount <= this.money) {
            this.money -= amount;
            return true;
        }
        return false; // กรณีเงินไม่พอหรือจำนวนเงินไม่ถูกต้อง
    }

    public int getMoney() {
        return this.money;
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

    public void deposit(int amount) {
        if (amount > 0) {
            this.money += amount;
            System.out.println("Deposit successful. New balance: " + this.money);
        } else {
            System.out.println("Deposit failed. Invalid amount.");
        }
    }

    public void withdraw(int amount) {
        if (amount > 0 && amount <= this.money) {
            this.money -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + this.money);
        } else {
            System.out.println("Withdrawal failed. Insufficient funds.");
        }
    }
}