package service;
import domain.Manager;
import repository.ManagerRepository;
public class AuthService {

		private ManagerRepository managerRepository;
		
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
}
