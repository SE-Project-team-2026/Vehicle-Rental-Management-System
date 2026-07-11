package service;

import observer.Observer;

public class EmailNotification implements Observer {
	private String email;

	public EmailNotification(String email) {
		this.email = email;
	}


	@Override
	public void update(String message) {
		System.out.println("Sending email to " + email + ": " + message);
	}

}
