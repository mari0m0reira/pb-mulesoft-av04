package br.com.compass.payment.model;

public class Costumer {
	private String document_type;
	private String document_number;
	
	public Costumer() {
		
	}
	
	public Costumer(String document_type, String document_number) {
		this.document_type = document_type;
		this.document_number = document_number;
	}
	public String getDocument_type() {
		return document_type;
	}
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	public String getDocument_number() {
		return document_number;
	}
	public void setDocument_number(String document_number) {
		this.document_number = document_number;
	}
	
	
}
