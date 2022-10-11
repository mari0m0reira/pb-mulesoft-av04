package br.com.compass.payment.model;

import java.math.BigDecimal;

public class PaymentRequest {
	private String seller_id;
	private Costumer costumer = new Costumer();
	private String payment_type;
	private String currency;
	private BigDecimal transaction_amount;
	private ResponseCard card = new ResponseCard();
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public Costumer getCostumer() {
		return costumer;
	}
	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(BigDecimal transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public ResponseCard getCard() {
		return card;
	}
	public void setCard(ResponseCard card) {
		this.card = card;
	}
	
	
}
