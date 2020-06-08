package com.bomgosto.services;

import com.bomgosto.domain.Cliente;
import com.bomgosto.repositories.ClienteRepository;
import com.bomgosto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService emailService;

    private final Random rand = new Random();

    public void sendNewPassword(String email){
        Cliente cliente = clienteRepository.findByEmail(email);

        if(!Optional.ofNullable(cliente).isPresent()){
            throw new ObjectNotFoundException("Email não encontrado!");
        }

        String newPass = newPassword();
        cliente.setSenha(pe.encode(newPass));
        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++){
            vet[i] = randomChar();
        }
        return String.valueOf(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        switch (opt){
            case 0:{
                //gera um dígito
                return (char) (rand.nextInt(10) + 48);
            }
            case 1:{
                //gera letra maiuscula
                return (char) (rand.nextInt(10) + 65);
            }
            default:{
                //gera letra minúscula
                return (char) (rand.nextInt(10) + 97);
            }
        }
    }
}
