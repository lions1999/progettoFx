package logic.engineeringclasses.bean;

public class UserBean {

	//private String userID;
    private String name;
    private String surname;
	private String email;
	private String password;
	private String role;
	private String okPwd;
	private String address;
    private int Id;

//	public String getID() {
//        return userID;
//    }
//
//    public void setID(String userID) {
//        this.userID = userID;
//    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email)	{
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getOkPassword() {
        return okPwd;
    }

    public void setOkPassword(String okPwd) {
        this.okPwd = okPwd;
    }
       
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setID(int id) {this.Id = id;}

    public int getID() {return this.Id;}
}
