package com.colanjo.agencia.controller;

import com.colanjo.agencia.Exception.PacienteException;
import com.colanjo.agencia.entity.Paciente;
import com.colanjo.agencia.repository.PacienteRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class PacienteController {

    private PacienteRespository pacienteRespository;
    private static final Logger log = LoggerFactory.getLogger(PacienteController.class);

    PacienteController(PacienteRespository pacienteRespository){
        this.pacienteRespository = pacienteRespository;
    }

    @GetMapping("/pacientes")
    List<Paciente> getAllPacientes(){
        HttpHeaders responseHeaders = new HttpHeaders();
        return pacienteRespository.findAll();
    }

    @GetMapping("/pacientes/{id}")
    Paciente getPaciente(@PathVariable Integer id){
        return pacienteRespository.findById(id)
                .orElseThrow(() -> new PacienteException(id));
    }
    @GetMapping("/pacientesmaleimc")
    List<Paciente> getMaleIMC(){

        List<Paciente> pacientes = pacienteRespository.findAll();
        return     pacientes.stream()
                .filter(paciente -> (paciente.getSexo().contains("Masculino")))
                .filter(paciente -> {
                    float IMC = paciente.getPeso() / (paciente.getAltura() * paciente.getAltura());
                    if (paciente.getIMC() >30) {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/pacientesfemaleimc")
    List<Paciente> getFemaleIMC(){

        List<Paciente> pacientes = pacienteRespository.findAll();
        return     pacientes.stream()
                .filter(paciente -> (paciente.getSexo().contains("Feminino")))
                .filter(paciente -> {

                    if (paciente.getIMC() >30) {
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }
    @GetMapping("/pacientespercentfemale")
    float getPercentFemalePercentIMC(){

        List<Paciente> pacientes = pacienteRespository.findAll();
        long   overIMC =     pacientes.stream()
                .filter(paciente -> (paciente.getSexo().contains("Feminino")))
                .filter(paciente -> {

                    if (paciente.getIMC() >30) {
                        return true;
                    }
                    return false;
                }).count();

        long allFemale = pacientes.stream()
                .filter(paciente -> (paciente.getSexo().contains("Feminino"))).count();

        float percent = (overIMC * 100)/allFemale;

        return percent;
    }

    @GetMapping("/pacientespercentmale")
    float getPercentMalePercentIMC(){

        List<Paciente> pacientes = pacienteRespository.findAll();
        long   overIMC =     pacientes.stream()
                .filter(paciente -> (paciente.getSexo().contains("Masculino")))
                .filter(paciente -> {

                    if (paciente.getIMC() >30) {
                        return true;
                    }
                    return false;
                }).count();

        long allFemale = pacientes.stream()
                .filter(paciente -> (paciente.getSexo().contains("Masculino"))).count();

        float percent = (overIMC * 100)/allFemale;

        return percent;
    }

    @GetMapping("/pacientesstate")
    public Map<String, Long> getPacienteState(){
        List<Paciente> pacientes = pacienteRespository.findAll();
        return pacientes.stream()
                .collect(Collectors.groupingBy(Paciente::getEstado, Collectors.counting()));
    }

    @GetMapping("/pacientestiposangue")
    public Map<String, Double> getPacienteTypeBlood(){
        List<Paciente> pacientes = pacienteRespository.findAll();
        return pacientes.stream()
                .collect(Collectors.groupingBy(Paciente::getTipo_sanguineo , Collectors.averagingInt(Paciente::getIdade)));
    }

    @GetMapping("/pacienteageimc")
    public  Map<String, Double> getPacienteAgeIMC(){
        List<Paciente> pacientes = pacienteRespository.findAll();
        Map<String, Double> map = new HashMap<>();


        map.put("0-10", pacientes.stream()
                .filter(p-> p.getIdade() > 0 && p.getIdade()<=10)
                .collect(Collectors.averagingDouble(Paciente::getIMC)));

        map.put("11-20", pacientes.stream()
                .filter(p-> p.getIdade() >= 11 && p.getIdade()<=20)
                .collect(Collectors.averagingDouble(Paciente::getIMC)));

        map.put( "21-30",pacientes.stream()
                .filter(p-> p.getIdade() >= 21 && p.getIdade()<=30)
                .collect(Collectors.averagingDouble(Paciente::getIMC)));

        map.put("31-40",  pacientes.stream()
                .filter(p-> p.getIdade() >= 31 && p.getIdade()<=40)
                .collect(Collectors.averagingDouble(Paciente::getIMC)));

        map.put("41-50",  pacientes.stream()
                .filter(p-> p.getIdade() >= 41 && p.getIdade()<=50)
                .collect(Collectors.averagingDouble(Paciente::getIMC)));

        map.put("51-60", pacientes.stream()
                .filter(p-> p.getIdade() >= 51 && p.getIdade()<=60)
                .collect(Collectors.averagingDouble(Paciente::getIMC)));

        map.put("61-70",   pacientes.stream()
                .filter(p-> p.getIdade() >= 61 && p.getIdade()<=70)
                .collect(Collectors.averagingDouble(Paciente::getIMC)));

        return  map;
    }

    @CrossOrigin(origins = "localhost:8080")
    @GetMapping("/pacientedonation")
    public  Map<String, Long> getPacienteDonation(){
        List<Paciente> pacientes = pacienteRespository.findAll();
        return   pacientes.stream()
                .filter(p-> p.getIdade() >= 16 && p.getIdade() <=69)
                .collect(Collectors.groupingBy(Paciente::getTipo_sanguineo ,  Collectors.counting()));
    }

}