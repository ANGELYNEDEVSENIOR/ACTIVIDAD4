package com.sistemadepagosibero.sistema_pagos_backed_ibero.services;


import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.sistemadepagosibero.sistema_pagos_backed_ibero.entities.Estudiante;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.entities.Pago;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.enums.PagoStatus;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.enums.TypePago;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.repository.EstudianteRepository;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.repository.PagoRepository;



public class PagoService {
    @Autowired
    private PagoRepository pagoRepository; // inyeccion de dependencias de pagoRepository
    // para interactar con la base de pagos
    @Autowired
    private EstudianteRepository estudianteRepository;// inyeccion de dependencias de estudianteRepository
    // para interactar con la informacion de base de estudiantes

    // **metodo para guardar el pago en la bd y almacenar un file pdf en el servidor
    /*
     * @param file estudiante archivo pdf que se suba al servidor
     * 
     * @param cantidad monto de pago realizado
     * 
     * @param type tipo de pago EFECTIVO, CHEUQE , DEPOSITO, TRANSFERENCIA
     * 
     * @param date fecha en la que se realizo el pago
     * 
     * @param codigoEstudiante codig del estudiante que realiza el pago
     * 
     * @return objeto Pago guardado en la base de datos
     * 
     * @throws IOException si ocurre un error al manejar el archivo file pdf
     * 
     */
    public Pago savePago(MultipartFile file, double cantidad, TypePago type, LocalDate date, String codigoEstudiante)
            throws IOException {

        // **construir la ruta donde se guardara el archivo dentro del sistema
        /*
         * System.getProperty("user.home"): obtiene la ruta del directorio personal del
         * usuario del actual SO
         * Paths.get: construir una ruta dentro del directorio personal en la carpeta
         * "enset-data/pagos"
         */
        Path folderPath = Paths.get(System.getProperty("user.home"), "enset-data", "pagos");
        // verificar si la carpeta existe sino la debemos crear
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }
        /// generamos un nombre unico para el archivo usando UUID (universally unique
        /// identifier)
        String fileName = UUID.randomUUID().toString();
        // creamos la ruta completa donde se guardara el archivo
        // construimos la ruta completa del archivo la extension del archivo es pdf
        Path filePath = Path.get(System.getProperty("user.home"), "enset-data", "pagos", fileName + ".pdf");
        // guardamos el archivo en la ruta especifica
        Files.copy(file.getInputStream(), filePath);
        // buscamos el estudiante que realiza el pago con su codigo
        Estudiante estudiante = estudianteRepository.findByCodigo(codigoEstudiante);
        // creamos un nuevo objeto Pago utilizando el patron de diseno Builder
        Pago pago = Pago.builder()
                .type(type)
                .status(PagoStatus.CREADO)
                .fecha(date)    
                .estudiante(estudiante)
                .cantidad(cantidad)
                .file(filePath.toUri().toString())// ruta del archivo almacenado
                .build();// construccion final del objeto pago
        return pagoRepository.save(pago);// guardamos el pago en la base de datos
    }

    public byte[] getArchivoPorId(Long PagoId) throws IOException {

        // buscamos un objeto pago en la bd por su ID
        Pago pago = pagoRepository.findById(PagoId).get();
        // *pago.getFile(): obtenemos la ruta URI del archivo
        /*
         * URI.create; convierte la cadena de texto en un objeto URI
         * pathOf: construye un objeto Path a partir de una URI
         * Files.readAllBytes: lee todos los bytes de un archivo y los devuelve como un
         * array de bytes
         * esto permite obtener el contenido del archivo para su porsterior uso por
         * ejemplo
         * descargar el archivo
         */
        return Files.readAllBytes(Path.of(URI.create(pago.getFile())));

    }

    public Pago actualizarPagoPorStatus(PagoStatus status, Long id) {
        // buscamos un objeto pago en la bd por su ID
        Pago pago = pagoRepository.findById(id).get();
        // actualizamos el estado de pago (validado o rechazado)
        pago.setStatus(status);
        // guardamos el pago en la bd
        return pagoRepository.save(pago);
        // obtenemos el archivo pdf
    }

}
