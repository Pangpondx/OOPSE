public class Manager extends Person{
    private String user;
    private String password;


    public Manager(String idCard,String name,String gender,String user,String password){
        super(idCard, name, gender);
        this.user = user;
        this.password = password;

    }
    public boolean checkLogin(String userLogin,String passwordLogin){
        return this.user.equals(userLogin) && this.password.equals(passwordLogin);


    }
}
