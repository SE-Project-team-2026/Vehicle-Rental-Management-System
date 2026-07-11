package service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import repository.ManagerRepository;

class AuthServiceTest {
	
	private AuthService authService;
	private ManagerRepository managerRepository;
	
	@BeforeEach
	public void setUp() {
		managerRepository = new ManagerRepository();
		authService = new AuthService(managerRepository);
	}

	@Test
	public void testLoginVerify_Success() {
		
		boolean result = authService.loginVerify("ahmad", "1234");
		assertTrue(result);
		
	}
	

	@Test
	public void testLoginVerify_WrongPassword() {
		
		boolean result = authService.loginVerify("ahmad", "1264");
		assertFalse(result);		
	}
	
	@Test
	public void testLoginVerify_InvalidEmail() {
		
		boolean result = authService.loginVerify("omar", "1234");
		assertFalse(result);		
	}
	
	@Test
	public void testLogin_Success() {
		authService.login("ahmad", "1234");
		assertTrue(authService.isLoggedIn());
	}
	
	@Test
	public void testLogin_Failure() {
		assertThrows(IllegalArgumentException.class, () -> {
			authService.login("ahmad", "1235");
		});
	}
	
	@Test
	public void testLogout() {
		authService.login("ahmad", "1234");
		assertTrue(authService.isLoggedIn()); 		
		
		authService.logout();
		
		assertFalse(authService.isLoggedIn());
		assertNull(authService.getCurrentManager());
	}
}
