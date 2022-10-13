package br.com.compass.payment.dto;

import java.math.BigDecimal;

import br.com.compass.payment.model.Item;
import br.com.compass.payment.model.Pedido;

public class ItemDto {
	private String item;
	private BigDecimal value;
	private long qty;
	
	public ItemDto() {
		
		
	}
	public ItemDto(Item item) {
		this.item = item.getItem();
		this.value = item.getValue();
		this.qty = item.getQty();
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public long getQty() {
		return qty;
	}
	public void setQty(long qty) {
		this.qty = qty;
	}
	
	
}
