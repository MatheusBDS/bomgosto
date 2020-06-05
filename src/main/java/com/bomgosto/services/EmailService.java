package com.bomgosto.services;

import org.springframework.mail.SimpleMailMessage;

import com.bomgosto.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
}
