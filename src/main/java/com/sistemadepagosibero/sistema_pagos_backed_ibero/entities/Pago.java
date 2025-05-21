package com.sistemadepagosibero.sistema_pagos_backed_ibero.entities;
import java.time.LocalDate;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.enums.PagoStatus;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.enums.TypePago;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Anotacion que indica que esta clase es una entidad
@Entity
//para la construccion de objetos con lombok
@Builder
//Anotacion que genera los getter, los setter. los equals y los hashcode
@Data
//Anotacion de un constructor sin parametros 
@NoArgsConstructor
//Anotacion de una libreria en lombok con todos los parametros
@AllArgsConstructor
public class Pago {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private double cantidad;
    private TypePago type;
    private PagoStatus status;
    private String file;

    @ManyToOne
    private Estudiante estudiante;
}
