package br.com.compass.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compass.payment.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
