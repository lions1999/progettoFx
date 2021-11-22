package logic.model;

public class Registered {

    private int Id;
    private String name;
	private String email;
	private String password;
	private String role;
	private String address;
	
	public Registered(int Id , String name, String email, String password, String role, String address){
		this.setId(Id);
        this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setRole(role);
		this.setAddress(address);
	}

    public int getId(){return Id;}

    public void setId(int Id){this.Id = Id;}

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
