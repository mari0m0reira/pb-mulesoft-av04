package br.com.compass.payment.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import br.com.compass.payment.dto.AuthDto;
import br.com.compass.payment.dto.AuthEnviarDto;
import br.com.compass.payment.dto.PaymentDto;
import br.com.compass.payment.model.Payment;
import br.com.compass.payment.model.Pedido;
import br.com.compass.payment.repository.PaymentRepository;
import br.com.compass.payment.service.PaymentService;

@RestController
@RequestMapping("api/v1/order/payment")
public class PaymentController {
	
	@Autowired
	private PaymentService service;
	
	
	
	@GetMapping
    public List<PaymentDto> list(){
        return service.findAllOrder();
    }

	@GetMapping("/{id}")
    public ResponseEntity<PaymentDto> listarPorId(@PathVariable @NotNull Long id) {
		return service.obterPorId(id);		
    }
		/*
	@PostMapping
	@Transactional
	public ResponseEntity<PaymentDto> post(@RequestBody @Valid Pedido form, UriComponentsBuilder uriBuider) {
		PaymentDto pedidoRealizado = service.criarPedido(form);
		
		URI uri = uriBuider.path("api/v1/estados/{id}").buildAndExpand(pedidoRealizado.getOrder_id()).toUri();
		return ResponseEntity.created(uri).body(pedidoRealizado);
		
		
		
		
	}
	*/
	@PostMapping
	@Transactional
	public ResponseEntity<PedidoDto> post(@RequestBody @Valid Pedido form, UriComponentsBuilder uriBuider) {
		//PaymentDto pedidoRealizado = service.criarPedido(form);
		//URI uri = uriBuider.path("api/v1/order/payment").buildAndExpand().toUri();
		//return ResponseEntity.created(uri).body(pedidoRealizado);
		
		System.out.println(form.getCpf() + "\n" +
							form.getShipping() 	+ "\n" +	
							form.getPayment().getCard_number() + "\n" +
							form.getPayment().getCardholder_name() + "\n" +
							form.getPayment().getSecurity_code() + "\n" +
							form.getPayment().getExpiration_month() + "\n" +
							form.getPayment().getExpiration_year() + "\n" +
							form.getPayment().getBrand() 
		);
		/*
			HttpEntity<AuthDto> requestBody = new HttpEntity<>(newEmployee, headers);	
			RestTemplate restTemplate = new RestTemplate();
			restTemplate = new RestTemplateBuilder().build();
			AuthDto deto;
			System.out.println("Teste 1:");
			//deto = restTemplate.getForObject("https://pb-getway-payment.herokuapp.com/v1/auth", AuthDto.class);
			deto = restTemplate.postForObject("https://pb-getway-payment.herokuapp.com/v1/auth",new AuthEnviarDto(), AuthDto.class);
			System.out.println("Teste" + deto);
		*/
		System.out.println("----------------------CHEGOU AQUI--------------------------");
			AuthDto dto = service.autenticar();
			System.out.println(dto.getAccess_token());
			System.out.println(dto.getToken_type());
			System.out.println(dto.getExpires_in());
					
		
		//retirar
		URI uri = uriBuider.path("api/v1/order/payment").buildAndExpand().toUri();
		return ResponseEntity.created(uri).body(form);
	}
	
	
}
