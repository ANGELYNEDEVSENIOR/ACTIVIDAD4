package web;

import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sistemadepagosibero.sistema_pagos_backed_ibero.entities.Estudiante;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.entities.Pago;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.enums.PagoStatus;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.enums.TypePago;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.repository.EstudianteRepository;
import com.sistemadepagosibero.sistema_pagos_backed_ibero.repository.PagoRepository;

@SpringBootApplication
public class SistemaPagosBackedIberoApplication {

	public static void main(String[] args) {
		// es el punto de entrada de la aplicacion
		SpringApplication.run(SistemaPagosBackedIberoApplication.class, args);
	}

	@Bean 
	CommandLineRunner commandLineRunner(EstudianteRepository estudianteRepository, 
	PagoRepository pagoRepository){ {
		return args -> {
			//guardar estudiante en la bd al iniciar la app
			estudianteRepository.save(Estudiante.builder()
			.id(UUID.randomUUID().toString())
			.nombre("Andres")
			.apellido("Perez")
			.codigo("1234")
			.programaId("IS123")
			.build());

			estudianteRepository.save(Estudiante.builder()
			.id(UUID.randomUUID().toString())
			.nombre("Felipe")
			.apellido("Lopez")
			.codigo("4567")
			.programaId("ID234")
			.build());

			estudianteRepository.save(Estudiante.builder()
			.id(UUID.randomUUID().toString())
			.nombre("Anamaria")
			.apellido("Martinez")
			.codigo("7890")
			.programaId("LC123")
			.build());
			
	estudianteRepository.save(Estudiante.builder()
    .id(UUID.randomUUID().toString())
    .nombre("Carlos")
    .apellido("Gomez")
    .codigo("1122")
    .programaId("LC123")
    .build());

estudianteRepository.save(Estudiante.builder()
    .id(UUID.randomUUID().toString())
    .nombre("Laura")
    .apellido("Diaz")
    .codigo("3344")
    .programaId("ID456")
    .build());

estudianteRepository.save(Estudiante.builder()
    .id(UUID.randomUUID().toString())
    .nombre("Pedro")
    .apellido("Ramirez")
    .codigo("5566")
    .programaId("AT789")
    .build());

estudianteRepository.save(Estudiante.builder()
    .id(UUID.randomUUID().toString())
    .nombre("Sofia")
    .apellido("Lopez")
    .codigo("7788")
    .programaId("LC123")
    .build());

estudianteRepository.save(Estudiante.builder()
    .id(UUID.randomUUID().toString())
    .nombre("Diego")
    .apellido("Perez")
    .codigo("9900")
    .programaId("IS456")
    .build());

estudianteRepository.save(Estudiante.builder()
    .id(UUID.randomUUID().toString())
    .nombre("Valeria")
    .apellido("Sanchez")
    .codigo("1011")
    .programaId("AT789")
    .build());

estudianteRepository.save(Estudiante.builder()
    .id(UUID.randomUUID().toString())
    .nombre("Andres")
    .apellido("Torres")
    .codigo("1213")
    .programaId("LC123")
    .build());
    //obtiene todos los valores posibles enumerados de la clase TypePago
	TypePago typePago[] = TypePago.values();
	//creamos un objeto radom para generar numeros aleatorios
	Random random = new Random();

	//iter sobre todos los estudiantes del repositorio
	estudianteRepository.findAll().forEach(estudiante -> {
		//creamos un pago para cada estudiante
		for(int i = 0; i < 10; i++) {
			//generamos un index aleatorio
			int index = random.nextInt(typePago.length);

			Pago pago = Pago.builder()
			.cantidad(1000 + (int) (Math.random()* 20000))
			.type(typePago[index])
			.status(PagoStatus.CREADO)
			.fecha(LocalDate.now())
			.estudiante(estudiante)
			.build();
			
			pagoRepository.save(pago);
		}
	});



		};
	
	}
}
}
