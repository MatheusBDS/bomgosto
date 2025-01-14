package com.bomgosto.services;

import javax.mail.internet.MimeMessage;

import com.bomgosto.domain.Cliente;
import org.springframework.mail.SimpleMailMessage;

import com.bomgosto.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	void sendHtmlEmail(MimeMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
