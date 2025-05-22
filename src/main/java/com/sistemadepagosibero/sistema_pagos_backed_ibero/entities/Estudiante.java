package com.sistemadepagosibero.sistema_pagos_backed_ibero.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class Estudiante {
   @Id //indicar que este campo es la primary key en la base de datos
   //usamos encapsulamiento y usamos String para numeros o letras
   private String id;
   private String nombre;
   private String apellido;

   //usamos column para indicar que este campo es unico en la base de datos
   @Column(unique = true)
   //usamos String para numeros o letras
   private String codigo;
// este campo nos indica el programa al que pertenece el estudiante
   private String programaId;
   private String foto;
}
