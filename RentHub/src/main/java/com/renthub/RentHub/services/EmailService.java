/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.renthub.RentHub.services;

import com.renthub.RentHub.models.entities.Alquiler;
import com.renthub.RentHub.models.entities.User;
import com.renthub.RentHub.models.entities.Vehiculo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(user.getUsername());
            helper.setSubject("✨ Confirmación de tu alquiler #" + alquiler.getIdalquiler());
            String logoUrl = "http://localhost:8080/images/logorent.jpg";
            String html = "<!DOCTYPE html>"
                + "<html><body style=\"font-family:Arial,sans-serif;color:#333;\">"

                // Logo al principio
                + "<div style=\"text-align:center;margin-bottom:20px;\">"
                + "  <img src=\"" + logoUrl + "\""
                + "       alt=\"RentHub Logo\""
                + "       style=\"width:150px;height:auto;\" />"
                + "</div>"

                + "<h2 style=\"color:#2a9d8f;\">¡Alquiler Confirmado!</h2>"
                + "<p>Hola <strong>" + user.getName() + "</strong>,</p>"
                + "<p>Tu alquiler se ha completado con éxito. Aquí tienes los detalles:</p>"
                + "<table style=\"width:100%;border-collapse:collapse;\">"
                + "  <tr>"
                + "    <td style=\"padding:8px;border:1px solid #ddd;\"><strong>Alquiler ID</strong></td>"
                + "    <td style=\"padding:8px;border:1px solid #ddd;\">" + alquiler.getIdalquiler() + "</td>"
                + "  </tr>"
                + "  <tr style=\"background:#f9f9f9;\">"
                + "    <td style=\"padding:8px;border:1px solid #ddd;\"><strong>Vehículo</strong></td>"
                + "    <td style=\"padding:8px;border:1px solid #ddd;\">"
                +        vehiculo.getMarca() + " " + vehiculo.getModelo()
                +   "</td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td style=\"padding:8px;border:1px solid #ddd;\"><strong>Inicio</strong></td>"
                + "    <td style=\"padding:8px;border:1px solid #ddd;\">"
                +        alquiler.getFechaInicio()
                +   "</td>"
                + "  </tr>"
                + "  <tr style=\"background:#f9f9f9;\">"
                + "    <td style=\"padding:8px;border:1px solid #ddd;\"><strong>Fin</strong></td>"
                + "    <td style=\"padding:8px;border:1px solid #ddd;\">"
                +        alquiler.getFechaFin()
                +   "</td>"
                + "  </tr>"
                + "  <tr>"
                + "    <td style=\"padding:8px;border:1px solid #ddd;\"><strong>Precio/m</strong></td>"
                + "    <td style=\"padding:8px;border:1px solid #ddd;\">"
                +        vehiculo.getPrecioMes() + " €"
                +   "</td>"
                + "  </tr>"
                + "</table>"
                + "<p style=\"margin-top:20px;\">Gracias por confiar en <strong>RentHub</strong>. ¡Disfruta tu viaje!</p>"
                + "<p>En las próximas <strong>48–72 horas</strong> nos pondremos en contacto contigo para la recogida del vehículo.</p>"
                + "<hr style=\"border:none;border-top:1px solid #eee;\"/>"
                + "<p style=\"font-size:0.9em;color:#777;\">Si no solicitaste este alquiler, contacta con soporte.</p>"
                + "</body></html>";

            helper.setText(html, true);
            mailSender.send(msg);
        } catch (MessagingException ex) {
            throw new RuntimeException("Error al enviar email de confirmación", ex);
        }
    }
}
