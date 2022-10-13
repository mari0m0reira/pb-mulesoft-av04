package br.com.compass.payment.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.compass.payment.dto.AuthDto;
import br.com.compass.payment.dto.AuthEnviarDto;
import br.com.compass.payment.dto.PaymentDto;
import br.com.compass.payment.http.AuthClient;
import br.com.compass.payment.model.Costumer;
import br.com.compass.payment.model.Item;
import br.com.compass.payment.model.Payment;
import br.com.compass.payment.model.PaymentRequest;
import br.com.compass.payment.model.Pedido;
import br.com.compass.payment.model.ResponseCard;
import br.com.compass.payment.model.ResponseRequest;
import br.com.compass.payment.model.Token;
import br.com.compass.payment.repository.PaymentRepository;
import br.com.compass.payment.security.Information;

@Service
public class PaymentService{
		
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PaymentRepository paymentRepository;	
	
	@Autowired
	private AuthClient authClient;
	
	//@Autowired
	private Token token = new Token();	
	 
	Information information = new Information();

	public List<PaymentDto> findAllOrder(){
		return paymentRepository.findAll().stream()
                .map(p -> modelMapper.map(p, PaymentDto.class))
                .collect(Collectors.toList());    }


	public ResponseEntity<PaymentDto> obterPorId(Long id) {
		 Optional<Payment> payment = paymentRepository.findById(id);
		 if(payment.isPresent()) {
				return ResponseEntity.ok(new PaymentDto(payment.get()));
			}else {
				return ResponseEntity.notFound().build();      
			}
	}
	
	public PaymentDto salvarPedido(Pedido pedido, ResponseRequest resp) {
			Payment pay = new Payment();
			pay.setTotal(calcularValorTotal(pedido));
			pay.setPayment_id(resp.getPayment_id());
			pay.setPayment_status(resp.getStatus());
			pay.setMessage(resp.getAutorization().getReason_message());
			paymentRepository.save(pay);
			return new PaymentDto(pay);
	}
				
	public void autenticar() {
		AuthEnviarDto enviar = new AuthEnviarDto();		
		enviar.setApi_key(information.getSeller_api_key());
		enviar.setClient_id(information.getSeller_client_id());
		AuthDto dto  = authClient.getAuth(enviar);
		token.setTokenValue(dto.getAccess_token());
		token.setTokenTime(dto.getExpires_in()*1000 + new Date().getTime());
		token.setTokenType(dto.getToken_type());
	}
	
	public BigDecimal calcularValorPedido(Pedido pedido) {
		List<Item>itens = pedido.getItems();
		BigDecimal valor = new BigDecimal("0");
		for(Item p : itens) {
			valor.add(p.getValue().multiply(BigDecimal.valueOf(p.getQty())));
		}
		return valor;
	}
	
	public BigDecimal calcularValorTotal(Pedido pedido) {
		BigDecimal valor = new BigDecimal("0");
		valor.add(pedido.getShipping());
		valor.add(calcularValorPedido(pedido));
		valor.subtract(pedido.getDiscount());
		return valor;
	}
	
	public PaymentDto criarPedido(Pedido pedido){	
		if(token == null  || token.getTokenTime() < new Date().getTime()) {
			autenticar();
		}
		ResponseRequest responseRequest = enviarCreditCard(pedido);
		PaymentDto salvo = salvarPedido(pedido, responseRequest);
		return salvo;
	}
		
	public ResponseRequest enviarCreditCard(Pedido pedido) {
		PaymentRequest paymentRequest = preencherPaymentRequest(pedido);
		String header = "Bearer " + token.getTokenValue();
		System.out.println("ANTES");
		System.out.println(header);
		printarPaymentRequest(paymentRequest);
		System.out.println(paymentRequest);
		ResponseRequest responseRequest = authClient.getRequest(header, paymentRequest);
		System.out.println("DEPOIS");
		
		System.out.println(responseRequest);
		return responseRequest;
	}
	
	 public PaymentRequest preencherPaymentRequest(Pedido pedido) {
		 	PaymentRequest paymentRequest = new PaymentRequest();
			paymentRequest.setSeller_id(information.getSeller_api_key());
			paymentRequest.setCostumer(preencherCostumer(pedido));
			paymentRequest.setPayment_type(pedido.getPayment_type());
			paymentRequest.setCurrency(pedido.getCurrency_type());
			paymentRequest.setTransaction_amount(calcularValorTotal(pedido));
			paymentRequest.setCard(preencherCard(pedido));			
			return paymentRequest;
	 }
	 
	 public Costumer preencherCostumer(Pedido pedido) {
		 Costumer costumer = new Costumer();
		 costumer.setDocument_type("CPF");
		 costumer.setDocument_number(pedido.getCpf());
		 return costumer;
	 }
	 
	 public ResponseCard preencherCard(Pedido pedido) {
		 ResponseCard card = new ResponseCard();
		 card.setNumber_token(token.getTokenValue());
		 card.setCardholder_name(pedido.getPayment().getCardholder_name());
		 card.setSecurity_code(pedido.getPayment().getSecurity_code());
		 card.setBrand(pedido.getPayment().getBrand());
		 card.setExpiration_month(String.valueOf(pedido.getPayment().getExpiration_month()));
		 card.setExpiration_year(String.valueOf(pedido.getPayment().getExpiration_year()));
		 return card;
	 }
	 
	 public void printarPaymentRequest(PaymentRequest pay) {
		 System.out.println("seller_id: " + pay.getSeller_id());
		 System.out.println("costumer: " + pay.getCostumer());
		 System.out.println("document_type: " + pay.getCostumer().getDocument_type());
		 System.out.println("document_number: " + pay.getCostumer().getDocument_number());
		 System.out.println("payment_type: " + pay.getPayment_type());
		 System.out.println("currency: " + pay.getCurrency());
		 System.out.println("transaction_amount " + pay.getTransaction_amount());
		 System.out.println("card: " + pay.getCard());
		 System.out.println("number_token: " + pay.getCard().getNumber_token());
		 System.out.println("cardholder_name: " + pay.getCard().getCardholder_name());
		 System.out.println("security_code: " + pay.getCard().getSecurity_code());
		 System.out.println("brand: " + pay.getCard().getBrand());
		 System.out.println("expiration_month: " + pay.getCard().getExpiration_month());
		 System.out.println("expiration_year: " + pay.getCard().getExpiration_year());
	 }
}
