public class Account {
    private String accountName;
    private String id;
    private String password;
    private int money;
    
    public Account(String name,String id, String password, int money){
        this.accountName = name;
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
        return this.accountName;
    }

    public String setNameAccount(String newName){
        accountName = newName;
        return this.accountName;
    }

    public boolean verifyID(String id) {
        return this.id.equals(id);
    }

    public boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

}