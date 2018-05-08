package de.rest.webservices.restfulwebservices.exception;

public class ErrorResponse {
	private String title;
	private String message;

	public ErrorResponse(String title, String message) {
		this.title = title;
		this.message = message;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

}
