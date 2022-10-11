package br.com.compass.payment.dto;

public class AuthorizationDto {
	private long authorization_code;
	private String authorized_at;
	private int reason_code;
	private String reason_message;
	public long getAuthorization_code() {
		return authorization_code;
	}
	public void setAuthorization_code(long authorization_code) {
		this.authorization_code = authorization_code;
	}
	public String getAuthorized_at() {
		return authorized_at;
	}
	public void setAuthorized_at(String authorized_at) {
		this.authorized_at = authorized_at;
	}
	public int getReason_code() {
		return reason_code;
	}
	public void setReason_code(int reason_code) {
		this.reason_code = reason_code;
	}
	public String getReason_message() {
		return reason_message;
	}
	public void setReason_message(String reason_message) {
		this.reason_message = reason_message;
	}
	
	
}
