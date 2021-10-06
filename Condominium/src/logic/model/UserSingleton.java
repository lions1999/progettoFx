package logic.model;

public class UserSingleton {

	private Administrator administrator = null;
	private Resident resident = null;
	private Owner owner = null;	
	private String usrId;
	private Role role;
	private String code;	
	//private Post post;
	private Request req;

	private static UserSingleton instance = null;

	private UserSingleton() {
	}

	public static UserSingleton getInstance() {
		if (instance == null)
			instance = new UserSingleton();
		return instance;
	}
	
	public void setUserID(String usrId) {
		this.usrId = usrId;
	}
	
	public String getUserID() {
		return this.usrId;
	}
	
	public Resident getResident() {
		return resident;
	}

	public void setResident(Resident resident) {
		this.resident = resident;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	public void setAdministrator(Administrator user) {
		this.administrator = user;
	}

	public Administrator getAdministrator() {
		return this.administrator;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return this.code;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
//	public Post getPost() {
//		return this.post;
//	}
//	
//	public void setPost(Post post) {
//		 this.post = post;
//	}
	
	public Request getReq() {
		return this.req;
	}

	public void setReq(Request req) {
		this.req = req;
	}
	
	
	
	public void clearState() {
		this.setUserID(null);
		this.setCode(null);
		this.setRole(null);
		this.setAdministrator(null);
		this.setResident(null);
		this.setOwner(null);
		//this.setPost(null);
		this.setReq(null);
	}



}
