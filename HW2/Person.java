public class Person {
    private String idCard;
    private String name;
    private String gender;

    public Person(String idCard,String name,String gender){
        this.idCard = idCard;
        this.name = name;
        this.gender = gender;
    }
    public String getId(){
        return this.idCard;
    }
    public String getName(){
        return this.name;
    }
    public String getGender(){
        return this.gender;
    }
}
