package it.aulab.progetto_finale_gianmarco_nacci.services;

public interface EmailService {
    void sendSimpleEmail(String to, String subject, String text);
}
