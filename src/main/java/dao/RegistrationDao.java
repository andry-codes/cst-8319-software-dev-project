package dao;

import java.util.List;
import java.util.UUID;

import beans.Register;
import services.RegistrationService;

public class RegistrationDao implements RegistrationService {

	@Override
	public List<Register> viewAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void newUser(Object register) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getID(UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateUser(Object register) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(UUID id) {
		// TODO Auto-generated method stub
		return false;
	}

}
