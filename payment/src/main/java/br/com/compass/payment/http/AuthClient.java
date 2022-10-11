package br.com.compass.payment.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.compass.payment.dto.AuthDto;
import br.com.compass.payment.dto.AuthEnviarDto;
import br.com.compass.payment.model.PaymentRequest;
import br.com.compass.payment.model.ResponseRequest;

@FeignClient(name = "auth", url = "https://pb-getway-payment.herokuapp.com", path = "/v1")
public interface AuthClient {
	
	@PostMapping("/auth")
	AuthDto getAuth(AuthEnviarDto authEnviarDto);

	@PostMapping("/payments/credit-card")
	ResponseRequest getRequest(@RequestHeader String string,  PaymentRequest paymentRequest);
}
