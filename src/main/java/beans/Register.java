package beans;
import java.util.UUID;

public class Register {
	// DB requires id, email, and username to all be unique. 
	private UUID userID; // generated at random when account is created.
	private String email;
	private String username;
	private String password;
	private boolean isVerified; // needs to be true to be able to login. 

	public UUID getUserID() {
		return userID;
	}

	public void setUserID(UUID userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    // constructor for creating new logs
    public Register(String email, String username, String password) {
    	this.userID = UUID.randomUUID();
    	this.email = email;
    	this.username = username;
    	this.password = password;
    	this.setVerified(false);
    }
    
    // constructor for pulling logs from db 
    public Register(UUID userID, String email, String username, String password, boolean isVerified) {
    	this.userID = userID;
    	this.email = email;
    	this.username = username;
    	this.password = password;
    	this.setVerified(isVerified);
    }

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
    
}
