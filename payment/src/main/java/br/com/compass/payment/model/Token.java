package br.com.compass.payment.model;

public class Token {
	private String tokenValue;
	private String tokenType;
	private long tokenTime;
	
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public long getTokenTime() {
		return tokenTime;
	}
	public void setTokenTime(long tokenTime) {
		this.tokenTime = tokenTime;
	}
	
	
	

}
