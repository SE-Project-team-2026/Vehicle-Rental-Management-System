package service;
import domain.Manager;
import repository.ManagerRepository;
public class AuthService {

		private ManagerRepository managerRepository;
		private Manager currentManager;
		
		public AuthService(ManagerRepository managerRepository) {
			this.managerRepository = managerRepository;
		}

	public boolean loginVerify(String email, String password) {

		Manager foundManager = managerRepository.findByEmail(email);
		if (foundManager != null && foundManager.getPassword().equals(password)) {
			return true;
		}
		return false;
	}
	
	public Manager getCurrentManager() {
		return currentManager;
	}
	
	public void logout() {
		currentManager = null;
	}
	
	public boolean isLoggedIn() {
		return currentManager != null;
	}
	
	public void login(String email, String password) {
	    if (loginVerify(email, password)) {
	        currentManager = managerRepository.findByEmail(email);
	    } else {
	        throw new IllegalArgumentException("Invalid email or password");
	    }
	}
}
