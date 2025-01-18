public interface ATMAc {
    boolean checkable(Account loginAccount);

    boolean withdrawable(Account loginAccount, double amount, String wordWd);

    boolean depositable(Account loginAccount, double amount, String wordDps);

    boolean transferable(Account loginAccount, Account recipient, int amount);

    void addAccount(Account account);

    void addManagerAccount(Manager account);
}
