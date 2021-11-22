package logic.model;

public class Registered {

    private String id;
    private String name;
	private String email;
	private String password;
	private String role;
	private String address;
    private String apartment;
	
	public Registered(String id , String name, String email, String password, String role, String address, String apartment){
		this.setId(id);
        this.setName(name);
		this.setEmail(email);
		this.setPassword(password);
		this.setRole(role);
		this.setAddress(address);
        this.setApartment(apartment);
	}

    public String getId(){return id;}

    public void setId(String id){this.id = id;}

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

    private void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getApartment(){
        return apartment;
    }
}
