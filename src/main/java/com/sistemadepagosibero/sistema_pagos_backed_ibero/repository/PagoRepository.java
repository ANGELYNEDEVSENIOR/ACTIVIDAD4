package com.sistemadepagosibero.sistema_pagos_backed_ibero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemadepagosibero.sistema_pagos_backed_ibero.entities.Pago;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.enums.PagoStatus;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.enums.TypePago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> { 
// metodo personalizado que busque a una lista de pagos que pertenecen a un estudiante en especifico
List<Pago> findByEstudianteId(String codigo);
    
//metodo personalizado que busque el estado de los pagos CREADO, VALIDADO, RECHAZADO
List<Pago> findByStatus(PagoStatus status);

//metodo personalizado para buscar pagos por su tipo EFECTIVO, CHEUQE , DEPOSITO, TRANSFERENCIA
List<Pago> findByType(TypePago type);
}
