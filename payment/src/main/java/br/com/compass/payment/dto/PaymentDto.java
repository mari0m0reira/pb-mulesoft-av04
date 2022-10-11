package br.com.compass.payment.dto;

import java.math.BigDecimal;

import br.com.compass.payment.model.Payment;


public class PaymentDto {
	
	private Long order_id;
	private BigDecimal total;
	private String payment_id;
	private String payment_status;
	private String message;
	
	public PaymentDto() {
		
	}
	public PaymentDto(Payment payment) {
		this.order_id = payment.getOrder_id();
		this.total = payment.getTotal();
		this.payment_id = payment.getPayment_id();
		this.payment_status = payment.getPayment_status();
		this.message = payment.getMessage();
	}

	
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
	

}
