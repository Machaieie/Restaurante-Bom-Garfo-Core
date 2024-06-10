package com.restaurantebomgarfocore.Restaurante_Bom_Garfo.model;

import java.io.Serializable;
import java.util.List ;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.CascadeType;


@Entity
@Table(name = "Reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reserva implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String date;
    private String time;
    private int numberPeople;
    private String observations;

   

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pagamento> pagamentos;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Pedido> pedidos;

    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
    @JsonIgnore
    private Conta conta;

  
    public void criarConta() {
        this.conta = new Conta();
        this.conta.setReserva(this);
        this.conta.setTotal(0.0); 
    }

}
