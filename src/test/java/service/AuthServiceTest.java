package service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import repository.ManagerRepository;

class AuthServiceTest {

	@Test
	public void testLoginVerify_Success() {
		
		ManagerRepository managerRepository = new ManagerRepository();
		AuthService authService = new AuthService(managerRepository);
		boolean result = authService.loginVerify("ahmad", "1234");
		assertTrue(result);
		
	}
	

	@Test
	public void testLoginVerify_WrongPassword() {
		
		ManagerRepository managerRepository = new ManagerRepository();
		AuthService authService = new AuthService(managerRepository);
		boolean result = authService.loginVerify("ahmad", "1264");
		assertFalse(result);		
	}
	
	@Test
	public void testLoginVerify_InvalidEmail() {
		
		ManagerRepository managerRepository = new ManagerRepository();
		AuthService authService = new AuthService(managerRepository);
		boolean result = authService.loginVerify("omar", "1234");
		assertFalse(result);		
	}
	
}
