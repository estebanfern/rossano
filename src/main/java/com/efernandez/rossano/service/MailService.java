package com.efernandez.rossano.service;

public interface MailService {
    void sendNewUserEmail(String email, String nombre, String username, String password);
    void sendNewUserEmailBasic(String email, String nombre, String username, String password);
}
