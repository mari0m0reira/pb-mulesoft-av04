package br.com.compass.payment.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
	private String cpf;
	private List<Item> items = new ArrayList<>();
	private BigDecimal shipping;
	private BigDecimal discount;
	private String payment_type;
	private String currency_type;
	private Card payment = new Card();
	
	
	
	public Pedido(String cpf, List<Item> items, BigDecimal shipping, BigDecimal discount, String payment_type, String currency_type,
			Card payment) {
		this.cpf = cpf;
		this.items = items;
		this.shipping = shipping;
		this.discount = discount;
		this.payment_type = payment_type;
		this.currency_type = currency_type;
		this.payment = payment;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public BigDecimal getShipping() {
		return shipping;
	}
	public void setShipping(BigDecimal shipping) {
		this.shipping = shipping;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getCurrency_type() {
		return currency_type;
	}
	public void setCurrency_type(String currency_type) {
		this.currency_type = currency_type;
	}
	
	public Card getPayment() {
		return payment;
	}
	public void setPayment(Card payment) {
		this.payment = payment;
	}
		
}

