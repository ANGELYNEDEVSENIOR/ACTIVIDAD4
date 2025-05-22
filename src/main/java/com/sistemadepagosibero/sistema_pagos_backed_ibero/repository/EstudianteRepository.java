package com.sistemadepagosibero.sistema_pagos_backed_ibero.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sistemadepagosibero.sistema_pagos_backed_ibero.entities.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, String> {
    //metodos personalizados que busquen a un estudiante por su codigo unico
    Estudiante findByCodigo(String codigo);
    // metodo personalizado que busque a una lista de estudiantes que pertenecen a un programa en especifico
    List<Estudiante> findByProgramaId(String programaId);
}
