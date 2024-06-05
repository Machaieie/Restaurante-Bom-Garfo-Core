package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Email;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.enums.EmailStatus;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository.EmailRepository;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
     EmailRepository emailRepository;
   
     @Autowired
     private JavaMailSender emailSender;

     public String sendEmail(Email emailDetails) {
         emailDetails.setSendDateEmail(LocalDateTime.now());
         try{
             SimpleMailMessage message = new SimpleMailMessage();
             message.setFrom(emailDetails.getEmailFrom());
             message.setTo(emailDetails.getEmailTo());
             message.setSubject(emailDetails.getSubject());
             message.setText(emailDetails.getText());
             emailSender.send(message);

             emailDetails.setStatusEmail(EmailStatus.SENT);
         } catch (MailException e){
             emailDetails.setStatusEmail(EmailStatus.ERROR);
         } finally {
            return "email enviado com sucesso";
         }
     }

     public Page<Email> findAll(Pageable pageable) {
         return  emailRepository.findAll(pageable);
     }

     public Optional<Email> findById(long emailId) {
         return emailRepository.findById(emailId);
     }

     @Async
	public void send(String to, String subject, String mail) {

		try {

			MimeMessage mimeMessage = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setTo(to);
			helper.setFrom("restaurantegarfodorado@gmail.com", "Restaurante Bom Garfo Dourado");
			helper.setSubject(subject);
			helper.setText(mail, true);
			emailSender.send(mimeMessage);

		} catch (Exception e) {
			//throw new BusinessException("Ocorreu um erro ao enviar o email");
		}
	}
}
