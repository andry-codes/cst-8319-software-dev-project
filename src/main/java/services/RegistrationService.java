package services;

import java.util.List;
import java.util.UUID;

import beans.Register;

public interface RegistrationService<Register> {

	void newUser(Register user);

	boolean userCheck(String username);
	
	boolean emailCheck(String email);
	
}
