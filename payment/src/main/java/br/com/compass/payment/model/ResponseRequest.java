package br.com.compass.payment.model;

import java.math.BigDecimal;

public class ResponseRequest {

	private String payment_id;
	private String seller_id;
	private BigDecimal transaction_amount;
	private String currency;
	private String status;
	private String received_at;
	private Authorization autorization;
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public BigDecimal getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(BigDecimal transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReceived_at() {
		return received_at;
	}
	public void setReceived_at(String received_at) {
		this.received_at = received_at;
	}
	public Authorization getAutorization() {
		return autorization;
	}
	public void setAutorization(Authorization autorization) {
		this.autorization = autorization;
	}
	
	
}
