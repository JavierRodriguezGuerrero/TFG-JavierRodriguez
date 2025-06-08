/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.services;

import com.renthub.RentHub.models.entities.Alquiler;
import com.renthub.RentHub.models.entities.User;
import com.renthub.RentHub.models.entities.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author javie
 */
@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Value("${spring.mail.from}")
    private String from;
    
    public void sendRentalConfirmation(User user, Vehiculo vehiculo, Alquiler alquiler) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(user.getUsername()); // asumimos que username es el email
        msg.setSubject("Confirmación de tu alquiler #" + alquiler.getIdalquiler());
        msg.setText(
            "Hola " + user.getName() + ",\n\n" +
            "Tu alquiler ha sido procesado con éxito:\n" +
            "– Vehículo: " + vehiculo.getMarca() + " " + vehiculo.getModelo() + "\n" +
            "– ID de alquiler: " + alquiler.getIdalquiler() + "\n" +
            "– Fecha inicio: " + alquiler.getFechaInicio() + "\n" +
            "– Fecha fin   : " + alquiler.getFechaFin()   + "\n\n" +
            "Gracias por confiar en nosotros.\n" +
            "Saludos,\nEl equipo de RentHub"
        );
        mailSender.send(msg);
    }
}
