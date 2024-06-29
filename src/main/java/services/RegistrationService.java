package services;

import java.util.List;
import java.util.UUID;

import beans.Register;

public interface RegistrationService<Register> {

	void newUser(Register user);
	
	Register getUser(UUID id);
	
	boolean updateUser(Register user);
	
	boolean deleteUser(UUID id);
	
}
