public interface ATMAc {
    boolean checkable(Account loginAccount);

    boolean withdrawable(Account loginAccount, int amount);

    boolean depositable(Account loginAccount, int amount);

    boolean transferable(Account loginAccount, Account recipient, int amount);

    void addAccount(Account account);

    void addManagerAccount(Manager account);
}