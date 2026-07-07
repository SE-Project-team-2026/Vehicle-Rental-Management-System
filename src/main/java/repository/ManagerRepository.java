package repository;
import domain.Manager;
import java.util.ArrayList;
import java.util.List;
public class ManagerRepository {

	private List<Manager> managers = new ArrayList<>();
	
	public ManagerRepository() {
		// Add a default manager for testing purposes
		managers.add(new Manager(1, "ahmad", "1234"));
	}
	
	public Manager findByEmail(String email) {
		for (Manager manager : managers) {
			if (manager.getEmail().equals(email)) {
				return manager;
			}
		}
		return null;
	}
	
}
