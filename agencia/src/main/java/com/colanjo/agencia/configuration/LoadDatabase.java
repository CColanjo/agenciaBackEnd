package com.colanjo.agencia.configuration;

import com.colanjo.agencia.entity.Paciente;
import com.colanjo.agencia.repository.PacienteRespository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(PacienteRespository pacienteRespository) {


        return args -> {


            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Paciente>> typeReference = new TypeReference<List<Paciente>>(){};
            InputStream inputStream = TypeReference.class.getResourceAsStream("/templates/data.json");
            try {
                List<Paciente> pacientes = mapper.readValue(inputStream,typeReference);
                pacientes           .forEach(p -> log.info("Preloading "  + pacienteRespository.save(new Paciente(p.getId(),  p.getNome(), p.getCpf() , p.getRg(), p.getData_nasc() , p.getSexo() , p.getMae() , p.getPai() , p.getEmail() , p.getCep() , p.getEndereco() , p.getNumero() , p.getBairro() , p.getBairro() , p.getEstado() , p.getTelefone_fixo() , p.getCelular() , p.getAltura() , p.getPeso() , p.getTipo_sanguineo() ) )));

            } catch (IOException e){
                System.out.println("Unable to save pacientes: " + e.getMessage());
            }
        };
    }

}