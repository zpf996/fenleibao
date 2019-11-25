package online.zpf666.fenleibao.bean;

public class user {
    private  int id;
    private  String passwd;
//    private  String name;
//    private int phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public String getPasswd()
    {
        return passwd;
    }
/*
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
*/
    @Override
    public String toString() {
        return "user{"+"id="+id+",passwd="+passwd+"/"+",name="+'}';
    }
    //name+"/"+",phone="+phone
}
