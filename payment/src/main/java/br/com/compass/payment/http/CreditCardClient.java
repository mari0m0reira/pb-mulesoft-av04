package br.com.compass.payment.http;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("https://pb-getway-payment.herokuapp.com")
public interface CreditCardClient {

}
