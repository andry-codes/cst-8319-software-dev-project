package services;

import java.util.List;
import java.util.UUID;

import beans.Register;

public interface RegistrationService<Register> {

	List<Register> viewAllUsers();
	
	void newUser(Register register);
	
	Register getID(UUID id);
	
	boolean updateUser(Register register);
	
	boolean deleteUser(UUID id);
	
}
