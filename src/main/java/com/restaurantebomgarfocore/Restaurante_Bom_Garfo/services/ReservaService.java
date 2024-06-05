package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.Reserva;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.dto.ReservaDTO;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.EmptyDatabaseException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model.exceptions.ResourceNotFoundException;
import com.restaurantebomgarfocore.Restaurante_Bom_Garfo.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private EmailService emailService;

    public String save(ReservaDTO entity) throws ResourceNotFoundException {
        Reserva reserva = new Reserva();
        reserva.setTime(entity.time());
        reserva.setFirstName(entity.firstName());
        reserva.setLastName(entity.lastName());
        reserva.setEmail(entity.email());
        reserva.setPhone(entity.phone());
        reserva.setNumberPeople(entity.numberPeople());
        reserva.setObservations(entity.observations());
        reserva.setDate(entity.date());
        reserva.criarConta();
        reservaRepository.save(reserva);
        
       // Prepare email content
       String to = entity.email();
       String subject = "Confirmação de Reserva";
       String htmlContent = "<html>"
       + "<body>"
       + "<h2>Olá " + entity.firstName() + ",</h2>"
       + "<p>A sua reserva foi confirmada!</p>"
       + "<p>Detalhes da reserva:</p>"
       + "<ul>"
       + "<li>Data: " + entity.date() + "</li>"
       + "<li>Hora: " + entity.time() + "</li>"
       + "<li>Número de pessoas: " + entity.numberPeople() + "</li>"
       + "</ul>"
       + "<p>Esperamos vê-lo em breve no nosso restaurante!</p>"
       + "<p>Se precisar de mais informações, entre em contato conosco pelo WhatsApp: "
       + "+258 85 289 5627"
       + "</p>"
       + "<p>Atenciosamente,</p>"
       + "<p>Restaurante Bom Garfo Dourado</p>"
       + "</body>"
       + "</html>";
    //    String mailContent = "Olá " + entity.firstName() +"\nA sua reserva foi marcada para "+entity.date()+" as "+entity.time() +" para "+entity.numberPeople()+" pessoas";
       emailService.send(to, subject, htmlContent);
        return "Reserva Criada com sucesso";
    }

    public Reserva findById(Long id) throws IllegalArgumentException {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva com id: " + id + " não encontrada"));
    }

    public List<Reserva> findAll() {
        List<Reserva> reservas = reservaRepository.findAll();
        if (reservas.isEmpty()) {
            throw new EmptyDatabaseException("Nenhuma reserva foi encontrada");
        }
        return reservas;
    }

    public String deleteById(Long id) throws IllegalArgumentException {
        Reserva foundedReserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva com id: " + id + " não encontrada"));
        reservaRepository.delete(foundedReserva);

        return "Reserva excluida com sucesso";
    }

    public String update(long id, ReservaDTO entity) {
        Reserva foundedReserva = reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reserva com id: " + id + " não encontrada"));

        // Update the existing reserva with the new values
        foundedReserva.setTime(entity.time());
        foundedReserva.setFirstName(entity.firstName());
        foundedReserva.setLastName(entity.lastName());
        foundedReserva.setEmail(entity.email());
        foundedReserva.setPhone(entity.phone());
        foundedReserva.setNumberPeople(entity.numberPeople());
        foundedReserva.setObservations(entity.observations());
        foundedReserva.setDate(entity.date());

        // Save the updated reserva
        reservaRepository.save(foundedReserva);

        return "Reserva atualizada com sucesso";
    }

}
