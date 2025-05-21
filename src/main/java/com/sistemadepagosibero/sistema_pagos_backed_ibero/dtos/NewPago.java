package com.sistemadepagosibero.sistema_pagos_backed_ibero.dtos;

import java.time.LocalDate;

import org.springframework.cglib.core.Local;

import com.sistemadepagosibero.sistema_pagos_backed_ibero.enums.TypePago;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPago {

    private double cantidad;
    private TypePago type;
    private LocalDate fecha;
    private String codigoEstudiante;
    
}
